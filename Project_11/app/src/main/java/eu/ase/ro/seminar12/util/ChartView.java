package eu.ase.ro.seminar12.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Map;
import java.util.Random;

import eu.ase.ro.seminar12.R;

@SuppressLint("ViewConstructor")
public class ChartView extends View {

    private Map<String, Integer> source;
    private Paint paint;
    private Random random;
    private Context context;

    public ChartView(Context context, Map<String, Integer> source) {
        super(context);
        this.context = context;
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (source == null || source.isEmpty()) {
            return;
        }
        //calculam valoarea maxim pentru a determina cea mai mare bara
        int maxValue = extractMaxValue();
        float widthBar = getWidth() / source.size();
        drawValues(canvas, maxValue, widthBar);
    }

    private void drawValues(Canvas canvas, int maxValue, float widthBar) {
        int currentBarPosition = 0;
        for (String label : source.keySet()) {
            drawValue(canvas, maxValue, widthBar, currentBarPosition, label);
            currentBarPosition++;
        }
    }

    private void drawValue(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, String label) {
        //valoare bara
        int value = source.get(label);
        //generare culoare
        int color = generateColor();
        paint.setColor(color);
        //trasare bara pozitia curenta
        drawBar(canvas, maxValue, widthBar, currentBarPosition, value);
        //desenare label
        drawLabel(canvas, widthBar, currentBarPosition, label, value);
    }

    private void drawLabel(Canvas canvas, float widthBar, int currentBarPosition, String label, int value) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.3 * widthBar));
        float x = (float) (currentBarPosition + 0.5) * widthBar;
        float y = (float) (0.95 * getHeight());
        canvas.rotate(270, x, y);
        canvas.drawText(context.getString(R.string.chart_label_format, label, value), x, y, paint);
        canvas.rotate(-270, x, y);
    }

    private void drawBar(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, int value) {
        float x1 = currentBarPosition * widthBar;
        float y1 = (1 - (float) (value) / maxValue) * getHeight();
        float x2 = x1 + widthBar;
        float y2 = getHeight();
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    private int generateColor() {
        return Color.argb(100, 1 + random.nextInt(254),
                1 + random.nextInt(254),
                1 + random.nextInt(254));
    }

    private int extractMaxValue() {
        int maxValue = 0;
        for (Integer value : source.values()) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}