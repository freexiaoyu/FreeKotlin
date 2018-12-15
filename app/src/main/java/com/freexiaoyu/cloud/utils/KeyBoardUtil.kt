package com.freexiaoyu.cloud.utils

import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.Editable
import android.text.InputType
import android.view.View
import android.widget.EditText

/**
 * Created by DIY on 2018-10-16. 18:34
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:自定义 键盘工具类
 */

class KeyBoardUtil
/***
 *
 * @param keyboardView KeyboardView 控件
 * @param editText  EditText控件
 * @param xmlId  数字和字母XML布局文件
 */
(private val keyboardView: KeyboardView, private val editText: EditText, xmlId: Int) {
    private val k1: Keyboard// 自定义键盘

    private val listener = object : KeyboardView.OnKeyboardActionListener {

        override fun swipeUp() {}

        override fun swipeRight() {

        }

        override fun swipeLeft() {}

        override fun swipeDown() {}

        override fun onText(text: CharSequence) {}

        override fun onRelease(primaryCode: Int) {}

        override fun onPress(primaryCode: Int) {}

        override fun onKey(primaryCode: Int, keyCodes: IntArray) {
            val editable = editText.text
            val start = editText.selectionStart
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> if (editable != null && editable.length > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start)
                    }
                }
                Keyboard.KEYCODE_CANCEL -> keyboardView.visibility = View.GONE
                else -> editable!!.insert(start, Character.toString(primaryCode.toChar()))
            }
        }
    }

    init {
        //setInputType为InputType.TYPE_NULL   不然会弹出系统键盘
        editText.inputType = InputType.TYPE_NULL
        k1 = Keyboard(editText.context, xmlId)
        this.keyboardView.setOnKeyboardActionListener(listener)
        this.keyboardView.keyboard = k1
        this.keyboardView.isEnabled = true
        this.keyboardView.isPreviewEnabled = false
    }

    // Activity中获取焦点时调用，显示出键盘
    fun showKeyboard() {
        val visibility = keyboardView.visibility
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.visibility = View.VISIBLE
        }
    }

    // 隐藏键盘
    fun hideKeyboard() {
        val visibility = keyboardView.visibility
        if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
            keyboardView.visibility = View.GONE
        }
    }
}
