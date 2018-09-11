package com.ybxcc.testinysx.ybxdemoset.fingerprint_recognition;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ybxcc.testinysx.ybxdemoset.MainActivity;
import com.ybxcc.testinysx.ybxdemoset.R;

import javax.crypto.Cipher;

@TargetApi(23)
public class FingerprintDialogFragment extends DialogFragment {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;
    private Cipher cipher;

    private FingerprintActivity mainActivity;
    private TextView errorMsg;

    private boolean isSelfCancelled;

    public void setCopher(Cipher copher) {
        this.cipher = copher;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mainActivity = (FingerprintActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fingerprintManager = getContext().getSystemService(FingerprintManager.class);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fingerprint_dialog, container, false);
        errorMsg = v.findViewById(R.id.error_msg);
        TextView cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                stopListening();
            }
        });
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        startListening(cipher);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void startListening(Cipher cipher) {
        isSelfCancelled = false;
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(new FingerprintManager.CryptoObject(cipher), cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (!isSelfCancelled) {
                    errorMsg.setText(errString);
                    if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
                        Toast.makeText(mainActivity, errString, Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
                errorMsg.setText(helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(mainActivity, "指纹认证成功", Toast.LENGTH_LONG).show();
                mainActivity.onAuthenticated();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                errorMsg.setText("指纹认证失败，请再试一次");
            }
        }, null);


    }

    private void stopListening() {
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            cancellationSignal = null;
            isSelfCancelled = true;
        }

    }
}
