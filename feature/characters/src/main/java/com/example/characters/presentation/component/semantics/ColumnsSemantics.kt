package com.example.characters.presentation.component.semantics

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val NumColumnsKey = SemanticsPropertyKey<Int>("NumColumnsKey")
var SemanticsPropertyReceiver.numColumns by NumColumnsKey
