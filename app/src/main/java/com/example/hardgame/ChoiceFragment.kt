package com.example.hardgame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.hardgame.databinding.FragmentChoiceBinding
import java.lang.IndexOutOfBoundsException
import kotlin.system.exitProcess

class ChoiceFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBinding
    private val dataModel: DataModel by activityViewModels()
    private lateinit var listOfLines: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoiceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.parcedData.observe(activity as LifecycleOwner) {
            listOfLines = it
        }


        var ss = 0

        val array = arrayOf(
            "Прогуливаясь по лесу в столь замечательную солнечную погоду, вы замечаете какое-то движение в кустах. Ваши действия?",
            "1 Кину камень в кусты и буду на стороже",
            "2 Пойду и гляну что там",
            "3 Убегу прочь пока на меня не напали"
        )

        var text = array[0]
        var btn1 = array[1]
        var btn2 = array[2]
        var btn3 = array[3]

        fun inLogic1(temper: String) {

            val array1 = arrayListOf<String>()
            for (i in listOfLines) {
                if (i.startsWith(temper, 0, true)) {
                    Log.i("myTest", i)
                    array1.add(i)
                }
            }

            val array2 = arrayListOf(array1[0], array1[1], array1[2], array1[3])

            text = array2[0]
            btn1 = array2[1]
            btn2 = array2[2]
            btn3 = array2[3]

            dataModel.text.value = text.filter { it != '/' && !it.isDigit() }
            binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
            binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
            binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }
            Log.i("myTest", array1.size.toString())
        }

        fun inLogic2(temper: String) {
            val array1 = arrayListOf<String>()
            try {

                for (i in listOfLines) {
                    if (i.startsWith(temper, 0, true)) {
                        Log.i("myTest", i)
                        array1.add(i)
                    }
                }

                val array2 = arrayListOf(array1[1], array1[2], array1[3], array1[4])
                text = array2[0]
                btn1 = array2[1]
                btn2 = array2[2]
                btn3 = array2[3]

                dataModel.text.value = text.filter { it != '/' && !it.isDigit() }
                binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
                binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
                binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }
                Log.e("myTest", array1.size.toString())

            } catch (e: IndexOutOfBoundsException) {
                dataModel.text.value = "на этом пока все))) ждите продолжения..."
                binding.ch1.visibility = View.INVISIBLE
                binding.ch2.visibility = View.INVISIBLE
                binding.ch3.text = "выход"
                binding.ch3.setOnClickListener {
                    requireActivity().finish()
                    exitProcess(0)
                }
            }
        }

        dataModel.text.value = text
        binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
        binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
        binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }

        binding.ch1.setOnClickListener()
        {
            val temper =
                btn1.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }

        binding.ch2.setOnClickListener()
        {
            val temper =
                btn2.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }
        binding.ch3.setOnClickListener()
        {
            val temper =
                btn3.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChoiceFragment()
    }
}