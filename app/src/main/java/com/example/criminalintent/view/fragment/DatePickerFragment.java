package com.example.criminalintent.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.criminalintent.R;
import com.example.criminalintent.databinding.DialogFragmentDatePickerBinding;
import com.example.criminalintent.viewmodel.DatePickerViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String ARG_DATE = "currentDate";

    private DatePickerViewModel mDatePickerViewModel;
    private DialogFragmentDatePickerBinding mBinding;

    private Date mCurrentDate;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(Date currentDate) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, currentDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatePickerViewModel = new ViewModelProvider(this).get(DatePickerViewModel.class);
        mCurrentDate = (Date) getArguments().getSerializable(ARG_DATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_fragment_date_picker,
                null,
                false);

        initDatePicker();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setIcon(R.mipmap.ic_launcher)
                .setView(mBinding.getRoot())
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    Date datePicked = getSelectedDateFromDatePicker();
                    mDatePickerViewModel.setResult(DatePickerFragment.this, datePicked);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void initDatePicker() {
        //convert date to calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mCurrentDate);

        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mBinding.datePickerCrime.init(year, monthOfYear, dayOfMonth, null);
    }

    private Date getSelectedDateFromDatePicker() {
        int year = mBinding.datePickerCrime.getYear();
        int monthOfYear = mBinding.datePickerCrime.getMonth();
        int dayOfMonth = mBinding.datePickerCrime.getDayOfMonth();

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        return gregorianCalendar.getTime();
    }
}