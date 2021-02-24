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

public class ProductDetailBottomSheet extends BottomSheetDialogFragment {
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
        //  String description = sharedPreferences.getString("storedDescription", "default Value");

        String description = localDataManager.getSharedPreference(getActivity(),
                //burası benim link tarafım products/categories
                SharedPreferenceKeys.Description, "");
        // kayıt edilen Description çağırıldı
        bottomSheetDescription.setText(description);

        return view;//dönme işlemlerini tam anlamadım
    }
}
