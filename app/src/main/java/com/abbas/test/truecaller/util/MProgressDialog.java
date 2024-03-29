package com.abbas.test.truecaller.util;

import android.app.Dialog;
import android.content.Context;

import com.abbas.test.truecaller.R;


public class MProgressDialog {


    private final Dialog loading_dialog;

    public MProgressDialog(Context context) {

        loading_dialog = new Dialog(context);
        loading_dialog.setContentView(R.layout.item_progress_dialog);
        loading_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loading_dialog.setCancelable(false);
        loading_dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);

    }

    public void showLoadingDialog() {
//        loading_dialog.findViewById(R.id.imag).startAnimation(ProjectUtilAnim.animation_progress_dialog(1000));
        if (loading_dialog != null)
            loading_dialog.show();
    }

    public void dismissLoadingDialog() {
//        loading_dialog.findViewById(R.id.imag).clearAnimation();
        if (loading_dialog != null)
            loading_dialog.dismiss();
    }

        public boolean isShowingDialog() {
//        loading_dialog.findViewById(R.id.imag).clearAnimation();
            return loading_dialog.isShowing();
    }
}
