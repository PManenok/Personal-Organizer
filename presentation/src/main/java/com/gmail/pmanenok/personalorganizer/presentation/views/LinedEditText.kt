package com.gmail.pmanenok.personalorganizer.presentation.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.widget.EditText
import com.gmail.pmanenok.personalorganizer.R


class LinedEditText : EditText {
    private val linePaint = Paint()
    private var linePaintColor: Int = Color.BLACK
    private var leftX: Float = 0f
    private var rightX: Float = 0f

    constructor(context: Context) : super(context) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        initAttrs(attrs)
        initPaint()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttrs(attrs)
        initPaint()
    }

    /*  Initialize attributes from XML file  */
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinedEditText)
        linePaintColor = typedArray.getColor(R.styleable.LinedEditText_linesColor, Color.BLACK)
        typedArray.recycle()
    }

    private fun initPaint() {
        linePaint.strokeWidth = 1f
        linePaint.style = Paint.Style.STROKE
        linePaint.color = linePaintColor
    }


    override fun onDraw(canvas: Canvas) {
        leftX = 0f
        rightX = width.toFloat()

        val countLines = (height - paddingTop - paddingBottom) / lineHeight
        val count = if (countLines > lineCount) countLines else lineCount
        var i = 0
        while (i < count) {
            val baseline = (lineHeight * (i + 1) + paddingTop).toFloat()
            canvas.drawLine(leftX + paddingLeft, baseline, rightX - paddingRight, baseline, linePaint)
            i++
        }
        super.onDraw(canvas)
    }
}