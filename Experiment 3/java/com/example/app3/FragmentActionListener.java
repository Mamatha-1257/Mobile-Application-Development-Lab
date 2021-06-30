package com.example.app3;

import android.os.Bundle;

/**
 * Created by anildeshpande on 12/26/17.
 */

public interface FragmentActionListener {

    String KEY_SELECTED_CANDIDATE="KEY_SELECTED_CANDIDATE";

    void onCandidateSelected(String candidate);
}
