package com.generate.protocol

/**
 * Created by yangzhilei on 16/9/8.
 */
private fun line(count: Int): String {
    val builder = StringBuilder();
    for (x in 1..count) {
        builder.append("\n")
    }
    return builder.toString();
}

private fun tab(count: Int): String {
    val builder = StringBuilder();
    for (x in 1..count) {
        builder.append("    ");
    }
    return builder.toString();
}

private fun transField(wrap: String, field: Field): String {
    val builder = StringBuilder();
    builder.append("$wrap@Protocol(key = \"${field.key}\")\n")
    builder.append("${wrap}public ${fieldMapping(field)} ${field.getFieldName()}${newBean(field)};${line(2)}")
    return builder.toString();
}

private fun newBean(field: Field): String {
    when (field.fieldType) {
        FieldType.BEAN -> return " = new ${field.getBeanDeclare()}()";
        FieldType.LIST -> return " = new ArrayList<>()"
    }
    return ""
}

class OutputProtocol(val protocol: Protocol) {

    private val builder = StringBuilder();

    private fun append(text: String): StringBuilder {
        builder.append(text);
        return builder;
    }

    fun outProtocol(): String {
        begin();
        writeRequests();
        writeConstructor();
        writeOperation();
        writeRequestWithoutCookie();
        writeMock();
        writeResponse();
        writeBeans();
        end();
        return builder.toString();
    }

    private fun begin() {
        append("package ${protocol.packageName};${line(2)}")
        append("import com.evergrande.eif.net.api.base.HDAnnotationMtpProtocol;\n")
        append("import com.evergrande.eif.net.api.base.IHDRequestListener;\n")
        append("import org.json.JSONObject;\n")
        append("import java.util.ArrayList;${line(2)}")
        note("generated by CodeGenerate...");
        append("public class ${protocol.className} extends HDAnnotationMtpProtocol {${line(2)}")
    }

    private fun end() {
        append("}")
    }

    private fun writeConstructor() {
        append("    public ${protocol.className}(IHDRequestListener listener) {\n")
        append("        super(listener);\n");
        if (protocol.responseJsonPath != null) {
            append("        setResponseJsonPath(wrapResponseJsonPath(\"${protocol.responseJsonPath}\"));\n")
        }
        append("    }${line(2)}");
    }

    private fun writeOperation() {
        append("    @Override\n");
        append("    public String getOperation() {\n");
        append("        return \"${protocol.operation}\";\n");
        append("    }${line(2)}");
    }

    private fun writeRequestWithoutCookie() {
        if (protocol.requestWithoutCookie) {
            append("    @Override\n")
            append("    public boolean requestWithoutCookie() {\n")
            append("        return ${protocol.requestWithoutCookie};\n")
            append("    }${line(2)}")
        }
    }

    private fun writeMock() {
        append("    @Override\n")
        append("    public Object mockSuccess() {\n")
        append("        try {\n")
        append("            return new HDAnnotationMtpResponse<${protocol.responseClass}>() {}.parse(loadJsonObject());\n")
        append("        } catch (Exception e) {\n")
        append("        }\n")
        append("        return super.mockSuccess();\n")
        append("    }${line(2)}")
    }

    private fun note(note: String) {
        append("/**\n");
        append("  $note\n");
        append(" */\n");
    }

    private fun writeRequests() {
        for (field in protocol.requests) {
            append(transField(tab(1), field));
        }
    }

    private fun writeResponse() {
        if (protocol.responseClass != null) {
            append("    @Override\n")
            append("    public HDAnnotationMtpResponse<${protocol.responseClass}> transformSuccessResponse(JSONObject response) throws Exception {\n")
            append("        return new HDAnnotationMtpResponse<${protocol.responseClass}>(){}.parse(response);\n")
            append("    }${line(2)}")
        }
    }

    private fun writeBeans() {
        for (beanDeclare in protocol.beanPool.values) {
            append(OutputBean(beanDeclare).outBean())
        }
    }
}

private class OutputBean(val beanDeclare: BeanDeclare) {

    val builder = StringBuilder();

    fun outBean(): String {
        begin();
        for (field in beanDeclare.fields) {
            builder.append(transField("    ${wrap()}", field));
        }
        writeToString();
        end();
        return builder.toString();
    }

    fun begin() {
        append("public static class ${beanDeclare.className}{${line(2)}")

    }

    fun end() {
        append("}${line(2)}")
    }

    fun wrap(): String {
        return "    ";
    }

    fun append(text: String) {
        builder.append("${wrap()}$text");
    }

    fun writeToString() {
        append("    @Override\n")
        append("    public String toString() {\n")
        append("        StringBuilder builder = new StringBuilder();\n")
        for (field in beanDeclare.fields) {
            append("        append(builder, \"${field.key}\", ${field.getFieldName()});\n")
            if (field.fieldType == FieldType.LIST) {
                append("        if (${field.getFieldName()} != null) {\n")
                append("            builder.append(\"[\\n\");\n")
                append("            for (${field.getBeanDeclare()} obj : ${field.getFieldName()}) {\n")
                append("                builder.append(obj);\n")
                append("            }\n")
                append("            builder.append(\"]\");\n")
                append("        }\n")
            }
        }
        append("        return builder.toString();\n")
        append("    }\n")
    }
}