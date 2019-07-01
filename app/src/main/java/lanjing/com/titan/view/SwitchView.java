package lanjing.com.titan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenxi on 2019/4/16.
 */

public class SwitchView extends View {
    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }
}
