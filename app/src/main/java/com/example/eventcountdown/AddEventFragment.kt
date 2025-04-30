package com.example.eventcountdown

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment


class AddEventFragment : Fragment() {
    private lateinit var titleEditText: EditText
    private lateinit var timeTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var descriptionEditText: EditText
    private lateinit var doneButton: Button
    private lateinit var importantCheckBox: CheckBox


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = view.findViewById(R.id.editTextTitle)
        timeTextView = view.findViewById(R.id.textViewTime)
        dateTextView = view.findViewById(R.id.textViewDate)
        descriptionEditText = view.findViewById(R.id.editTextDescription)
        doneButton = view.findViewById(R.id.buttonDone)
        importantCheckBox = view.findViewById(R.id.checkBoxImportant)

        timeTextView.setOnClickListener { showTimePicker() }
        dateTextView.setOnClickListener { showDatePicker() }

        doneButton.setOnClickListener {
            saveEvent()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                val formattedTime = String.format("%02d:%02d %s",
                    if (hour > 12) hour - 12 else hour, minute,
                    if (hour >= 12) "PM" else "AM"
                )
                timeTextView.text = formattedTime
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePicker.show()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val formattedDate = String.format("%02d-%02d-%04d", day, month + 1, year)
                dateTextView.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun saveEvent() {
        val title = titleEditText.text.toString()
        val time = timeTextView.text.toString()
        val date = dateTextView.text.toString()
        val description = descriptionEditText.text.toString()
        val isImportant = importantCheckBox.isChecked

        if (title.isEmpty() || time.isEmpty() || date.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val event = EventModel(
            eventTitle = title,
            eventDate = date,
            eventTime = time,
            eventDescription = description,
            isImportant = isImportant
        )

        lifecycleScope.launch {
            val db = EventDatabase.getDatabase(requireContext())
            db.eventDao().insertEvent(event)

            Toast.makeText(requireContext(), "Event Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
