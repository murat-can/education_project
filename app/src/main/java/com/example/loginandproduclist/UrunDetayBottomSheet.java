package com.example.loginandproduclist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UrunDetayBottomSheet extends BottomSheetDialogFragment {
    TextView bottomSheetDescription;
    //public SharedPreferences sharedPreferences;
    LocalDataManager localDataManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.urndetay_bottom_sheet, container, false);
        bottomSheetDescription = view.findViewById(R.id.bottomid);
        localDataManager = new LocalDataManager();

        //  sharedPreferences = getActivity().getSharedPreferences("com.example.loginandproduclist", Context.MODE_PRIVATE);
        //   String description = sharedPreferences.getString("storedDescription", "default Value");
        // bottomSheetDescription.setText(description);
        String description = localDataManager.getSharedPreference(getActivity(), SharedPreferenceKeys.Description, "");
        bottomSheetDescription.setText(description);

        return view;
    }

}
