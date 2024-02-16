package com.example.registration.fragments

import android.os.Bundle
import android.text.Editable
import android.text.InputType.TYPE_CLASS_PHONE
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.core.interfaces.NavigationManager
import com.example.registration.R
import com.example.registration.databinding.FragmentRegistrationBinding
import com.example.registration.di.RegistrationComponentProvider
import com.example.setting_provider.SettingProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject


class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!
    private var isCorrectName = false
    private var isCorrectSurname = false
    private var isCorrectPhoneNumber = false

    @Inject
    lateinit var settingProvider: SettingProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegistrationBinding.bind(view)

        (requireActivity().application as RegistrationComponentProvider).getRegistrationComponent()
            .inject(this)

        binding.buttonInter.isEnabled = false
        initInputFields()
        setClickListener()
    }

    private fun initInputFields() {
        initPhoneNumberInput()
        initTextInputs()
    }

    private fun initTextInputs() = with(binding) {

        setInputTextListener(inputName, inputNameLayout) { isCorrect ->
            isCorrectName = isCorrect
            checkEnableButton()
        }
        setInputTextListener(inputSurname, inputSurnameLayout) { isCorrect ->
            isCorrectSurname = isCorrect
            checkEnableButton()
        }
    }

    private fun setInputTextListener(
        editText: TextInputEditText,
        layout: TextInputLayout,
        callback: (Boolean) -> Unit
    ) {
        editText.imeOptions = EditorInfo.IME_ACTION_NEXT
        editText.doAfterTextChanged { text: Editable? ->
            text?.let {
                text.forEach { char ->
                    if (!isCyrillic(char)) {
                        setBackground(editText, com.example.core.R.drawable.input_field_background_error)
                        setTintColor(layout, com.example.core.R.color.red)
                        callback(false)
                        return@let
                    }
                }
                setBackground(editText, com.example.core.R.drawable.input_field_background)
                setTintColor(layout, com.example.core.R.color.dark)
                callback(text.isNotEmpty())
            }
        }
    }

    private fun isCyrillic(char: Char): Boolean =
        (CYRILLIC.contains(char, ignoreCase = true))

    private fun setBackground(v: View, @DrawableRes res: Int) {
        v.background =
            ResourcesCompat.getDrawable(
                resources, res, requireActivity().theme
            )
    }
    private fun setTintColor(layout: TextInputLayout, @ColorRes color: Int) {
        layout.setEndIconTintList(ContextCompat.getColorStateList(requireContext(), color))
    }


    private fun initPhoneNumberInput() = with(binding) {

        val slots = UnderscoreDigitSlotsParser().parseSlots("+7 ___ ___ __ __")
        val watcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        watcher.installOn(inputPhoneNumber)

        inputPhoneNumber.inputType = TYPE_CLASS_PHONE

        inputPhoneNumberLayout.setEndIconOnClickListener {
            watcher.removeFromTextView()
            inputPhoneNumber.text = SpannableStringBuilder("")
            watcher.installOn(inputPhoneNumber)
        }
        inputPhoneNumber.doAfterTextChanged { editable ->
            editable?.let { text ->
                if (text.length == CORRECT_PHONE_NUMBER_LENGTH) {
                    isCorrectPhoneNumber = true
                    checkEnableButton()
                }
            }
        }
    }

    private fun checkEnableButton() {
        binding.buttonInter.isEnabled = isCorrectName && isCorrectSurname && isCorrectPhoneNumber
    }

    private fun setClickListener() {
        binding.buttonInter.setOnClickListener {
            settingProvider.registrationComplete()
            (requireActivity() as NavigationManager).startBottomNavigation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val CYRILLIC = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"
        const val CORRECT_PHONE_NUMBER_LENGTH = 16
    }
}