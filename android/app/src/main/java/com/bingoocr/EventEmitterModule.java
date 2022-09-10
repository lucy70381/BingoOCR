package com.bingoocr;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class EventEmitterModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext mReactContext;
    private static EventEmitterModule mEventEmitterModule;

    public EventEmitterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        mEventEmitterModule = this;
    }

    public static EventEmitterModule getInstance() {
        if (mEventEmitterModule == null) {
            mEventEmitterModule = new EventEmitterModule(mReactContext);
        }
        return mEventEmitterModule;
    }

    @Override
    public String getName() {
        return "EventEmitterModule";
    }

    public void sendEvent(String eventName, Object eventData) {
        // Note: Cannot assume react-context exists cause this is an async dispatched service.
        if (mReactContext != null && mReactContext.hasActiveCatalystInstance()) {
            mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, eventData);
        }
    }
}

