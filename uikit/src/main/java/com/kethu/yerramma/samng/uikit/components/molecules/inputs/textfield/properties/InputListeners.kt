package com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties

/**
 * @Author: Yerramma Kethu
 * @Date: 02/11/2025
 */
interface AdibTextFieldListeners {
    val onFocused: (Boolean) -> Unit get() = {}
    val onValueChange: (String) -> Unit
    val onImeActionClick: () -> Unit get() = {}
}

interface InputListeners : AdibTextFieldListeners {
    val onSelectionClick: () -> Unit
    val onInfoIconClick: () -> Unit
    val onSuffixClick: () -> Unit
}

class AdibInputListenersImpl(
    override val onValueChange: (String) -> Unit = {},
    override val onFocused: (Boolean) -> Unit = {},
    override val onImeActionClick: () -> Unit = {},
    override val onSelectionClick: () -> Unit = {},
    override val onInfoIconClick: () -> Unit = {},
    override val onSuffixClick: () -> Unit = {}
) : InputListeners