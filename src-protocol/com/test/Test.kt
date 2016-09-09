package com.test

import com.generate.protocol.*

/**
 * Created by yangzhilei on 16/8/19.
 */

fun main(args: Array<String>) {
    val protocol = Protocol("com.evergrande.eif.net.api.currentproduct", "HDMyCurrentProductDetailProtoAnnotationTest", "v1/currentproduct/op_my_current_product_detail.json", true);
    val bean_Response = BeanDeclare("Response");
    bean_Response.addField(Field(FieldType.BEAN, "myCurrentProductDetail").setBeanDeclare("MyCurrentProductDetail"))

    val bean_MyCurrentProductDetail = BeanDeclare("MyCurrentProductDetail")
    bean_MyCurrentProductDetail.addField(Field(FieldType.BEAN, "currentProductDetail").setBeanDeclare("CurrentProductDetail"))
    bean_MyCurrentProductDetail.addField(Field(FieldType.STRING, "totalAmount"))
    bean_MyCurrentProductDetail.addField(Field(FieldType.INT, "isPurchased"));
    bean_MyCurrentProductDetail.addField(Field(FieldType.STRING, "runningAmount"));

    val bean_CurrentProductDetail = BeanDeclare("CurrentProductDetail");
    bean_CurrentProductDetail.addField(Field(FieldType.INT, "productRank"))
    bean_CurrentProductDetail.addField(Field(FieldType.LONG, "productId"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "productName"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "weekAnnualYieldRate"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "accrualPerTenThousand"))
    bean_CurrentProductDetail.addField(Field(FieldType.LIST, "dayWeekAnnualYieldRates").setBeanDeclare("DayWeekAnnualYieldRate"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "productNo"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "productDesc"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "productCode"))
    bean_CurrentProductDetail.addField(Field(FieldType.INT, "investmentUnit"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "investmentUnitDesc"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "displayRate"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "valueDate"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "publishTime"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "valueTDay"))
    bean_CurrentProductDetail.addField(Field(FieldType.LONG, "baseProductId"))
    bean_CurrentProductDetail.addField(Field(FieldType.STRING, "perIncAmt"))

    val bean_DayWeekAnnualYieldRate = BeanDeclare("DayWeekAnnualYieldRate");
    bean_DayWeekAnnualYieldRate.addField(Field(FieldType.STRING, "date"))
    bean_DayWeekAnnualYieldRate.addField(Field(FieldType.STRING, "weekAnnualYieldRate"))
    bean_DayWeekAnnualYieldRate.addField(Field(FieldType.STRING, "incomePerMillion"))

    protocol.addBeanDeclare(bean_Response, bean_MyCurrentProductDetail, bean_CurrentProductDetail, bean_DayWeekAnnualYieldRate)

    protocol.responseClass = "Response";
    protocol.addRequest(Field(FieldType.LONG, "currentProductId"))
    println(OutputProtocol(protocol).outProtocol());
}