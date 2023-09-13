package com.example.myghmc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.cancel_qrdet).setOnClickListener(view1 -> {
            if(mListener !=null){
                dismiss();
            }
        });
        view.findViewById(R.id.closeBottomSheetButton).setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onBottomSheetDismissed();
            }
            dismiss();
        });
    }
}
