package tech.destinum.carmemo.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class NoImeEditText extends EditText {

    public NoImeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return false;
    }

}
