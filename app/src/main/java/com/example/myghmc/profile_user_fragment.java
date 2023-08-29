package com.example.myghmc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.viewmodel.CreationExtras;

public class profile_user_fragment extends Fragment implements View.OnClickListener{
    private TextView qr_code,lit_qr,details,lit_details,user_faq,lit_faq;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.profile_user_fragment, container, false);
        View view = inflater.inflate(R.layout.profile_user_fragment,container,false);
        qr_code= view.findViewById(R.id.user_qr_code);
        lit_qr = view.findViewById(R.id.lit_qr);
        details = view.findViewById(R.id.user_details);
        lit_details = view.findViewById(R.id.lit_details);
        user_faq = view.findViewById(R.id.user_faqs);
        lit_faq = view.findViewById(R.id.lit_faqs);
        qr_code.setOnClickListener(this);
        details.setOnClickListener(this);
        user_faq.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view==qr_code)
        {
            qr_code.setTextColor(Color.parseColor("#00ddff"));
            lit_qr.setBackgroundColor(Color.parseColor("#00ddff"));
            details.setTextColor(Color.parseColor("#000000"));
            lit_details.setBackgroundColor(0);
            user_faq.setTextColor(Color.parseColor("#000000"));
            lit_faq.setBackgroundColor(0);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.main_profile, new user_qr_code())
                    .commit();
        }
        if(view==details)
        {
            details.setTextColor(Color.parseColor("#00ddff"));
            lit_details.setBackgroundColor(Color.parseColor("#00ddff"));
            qr_code.setTextColor(Color.parseColor("#000000"));
            lit_qr.setBackgroundColor(0);
            user_faq.setTextColor(Color.parseColor("#000000"));
            lit_faq.setBackgroundColor(0);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.main_profile, new profilefragment())
                    .commit();
        }
        if(view==user_faq)
        {
            user_faq.setTextColor(Color.parseColor("#00ddff"));
            lit_faq.setBackgroundColor(Color.parseColor("#00ddff"));
            details.setTextColor(Color.parseColor("#000000"));
            lit_details.setBackgroundColor(0);
            qr_code.setTextColor(Color.parseColor("#000000"));
            lit_qr.setBackgroundColor(0);
        }
    }
}
