package com.example.myghmc;

import static android.widget.Toast.LENGTH_SHORT;

import static com.example.myghmc.Driver.jsonObj1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONObject;

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
        try {
            mListener.initialize();
            EditText useri = view.findViewById(R.id.userid);
            EditText zone = view.findViewById(R.id.zone);
            EditText circle = view.findViewById(R.id.circle);
            EditText plotno = view.findViewById(R.id.plotno);
            EditText totalunits = view.findViewById(R.id.totalunits);

            useri.setText(jsonObj1.getString("userId"));
            zone.setText(jsonObj1.getString("zone"));
            circle.setText(jsonObj1.getString("circle"));
            plotno.setText(jsonObj1.getString("plotNo"));
            totalunits.setText(jsonObj1.getString("totalUnits"));
        }catch (Exception e)
        {
            Toast.makeText(view.getContext(), e.getMessage(), LENGTH_SHORT).show();
            Log.e("msg",e.getMessage());
        }
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
