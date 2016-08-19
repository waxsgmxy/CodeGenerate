package com.generate.protocol

import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Created by yangzhilei on 16/8/19.
 */
class InputFrame : JFrame() {

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "协议代码生成器"
    }

}

class InputPanel : BasePanel() {

}

class InputItem : BasePanel() {

    init {

    }

}

open class BasePanel : JPanel() {
    init {
        isVisible = true
        layout = null
    }
}