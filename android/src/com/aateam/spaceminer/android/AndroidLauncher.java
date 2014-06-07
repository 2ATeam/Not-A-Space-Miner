package com.aateam.spaceminer.android;

import android.os.Bundle;
import com.aateam.spaceminer.core.STGame;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGL20 = true;
		initialize(new STGame(), config);
	}
}
