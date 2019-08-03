package com.example.sarwan.tawseel.extensions

import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.getConiditonDrawable(condition : Boolean, onTrue : Int, onFalse : Int) = resources.getDrawable(if (condition) onTrue else onFalse)