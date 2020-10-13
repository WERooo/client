package me.zeroeightsix.kami.gui.rgui

import me.zeroeightsix.kami.util.math.Vec2f

abstract class InteractiveComponent : Component() {
    // Interactive info
    protected var lastStateUpdateTime = System.currentTimeMillis(); private set
    protected var prevState = MouseState.NONE; private set
    var mouseState = MouseState.NONE
        private set(value) {
            prevState = field
            lastStateUpdateTime = System.currentTimeMillis()
            field = value
        }

    override fun onGuiInit() {
        super.onGuiInit()
        mouseState = MouseState.NONE
        prevState = MouseState.NONE
        lastStateUpdateTime = System.currentTimeMillis()
    }

    // Interactive methods
    open fun onMouseInput(mousePos: Vec2f) {

    }

    open fun onHover(mousePos: Vec2f) {
        mouseState = MouseState.HOVER
    }

    open fun onLeave(mousePos: Vec2f) {
        mouseState = MouseState.NONE
    }

    open fun onClick(mousePos: Vec2f, buttonId: Int) {
        mouseState = MouseState.CLICK
    }

    open fun onRelease(mousePos: Vec2f, buttonId: Int) {
        mouseState = if (isInComponent(mousePos)) MouseState.HOVER
        else MouseState.NONE
    }

    open fun onDrag(mousePos: Vec2f, clickPos: Vec2f, buttonId: Int) {
        mouseState = MouseState.DRAG
    }

    fun isInComponent(mousePos: Vec2f) = mousePos.x in 0.0f..width && mousePos.y in 0.0f..height

    @Suppress("UNUSED")
    enum class MouseState {
        NONE, HOVER, CLICK, DRAG
    }
}