package com.gmail.pmanenok.personalorganizer.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.TextView

class CellView : TextView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    private var pBorder: Paint = Paint()
    private var paint: Paint = Paint()
    private var rectF: RectF
    private var leftX: Float = 0f
    private var topY: Float = 0f
    private var rightX: Float = 0f
    private var bottomY: Float = 0f
    private var notes: ArrayList<Int> = arrayListOf()
    private val positionNull: Float = 13f
    private val radius: Float = 7f
    var dayId: Long = 0L
    private var defaultTextColor: Int = currentTextColor

    init {
        pBorder.strokeWidth = 5f
        pBorder.isAntiAlias = true
        pBorder.color = Color.BLACK
        pBorder.style = Paint.Style.STROKE;
        paint.strokeWidth = 5f
        paint.isAntiAlias = true
        rectF = RectF(0f, 0f, 0f, 0f)
    }

    private fun countBorders() {
        leftX = 0f
        topY = 0f
        rightX = width.toFloat()
        bottomY = height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        countBorders()
        rectF.set(leftX, topY, rightX, bottomY);
        canvas.drawRect(rectF, pBorder)
        drawNotes(canvas)
    }

    private fun drawNotes(canvas: Canvas) {
        var positionX = positionNull
        var positionY = positionNull
        for (note in notes) {
            setColor(note)
            canvas.drawCircle(leftX + positionX, bottomY - positionY, radius, paint)
            positionX += 18f
            if (positionX > rightX - positionNull) {
                positionY += 18f
                positionX = positionNull
            }
        }
    }

    private fun setColor(num: Int) = when (num) {
        1 -> {
            paint.color = Color.RED
        }
        2 -> {
            paint.color = Color.BLUE
        }
        3 -> {
            paint.color = Color.GREEN
        }
        else -> {
            paint.color = Color.BLACK
        }
    }

    fun setNotes(noteList: List<Int>?) {
        if (!noteList.isNullOrEmpty()) {
            if (notes.isNotEmpty()) notes.clear()
            notes.addAll(noteList)
        }
        invalidate()
    }

    fun clearNotes() {
        notes.clear()
        invalidate()
    }

    fun addNote(note: Int) {
        notes.add(note)
        invalidate()
    }

    fun addNotes(noteArray: ArrayList<Int>) {
        notes.addAll(noteArray)
        invalidate()
    }

    fun setBorderColor(alpha: Int, red: Int, green: Int, blue: Int) {
        pBorder.color = Color.argb(alpha, red, green, blue)
    }

    fun setBorderColor(red: Int, green: Int, blue: Int) {
        pBorder.color = Color.rgb(red, green, blue)
    }

    fun setBorderColor(color: Int) {
        pBorder.color = color
    }

    fun resetBorderColor() {
        pBorder.color = Color.BLACK
    }

    fun resetTextColor() {
        setTextColor(defaultTextColor)
    }

    fun setParams(dayId: Long, text: String, notesList: List<Int>?, borderColor: Int?, textColor: Int?) {
        this.dayId = dayId
        this.text = text
        if (notesList != null)
            setNotes(notesList)
        if (borderColor != null)
            setBorderColor(borderColor)
        if (textColor != null)
            setTextColor(textColor)
    }
}