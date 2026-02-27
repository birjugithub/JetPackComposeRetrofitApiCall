package com.jetpackcomposeroom

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposeroom.viewModel.PostViewModel

// ── Palette ──────────────────────────────────────────────────────────────────
private val Background   = Color(0xFF0D0D14)
private val Surface      = Color(0xFF16161F)
private val CardBg       = Color(0xFF1C1C28)
private val AccentGold   = Color(0xFFE8C97E)
private val AccentTeal   = Color(0xFF4ECDC4)
private val TextPrimary  = Color(0xFFF0EFE9)
private val TextSecondary= Color(0xFF8A8A9A)
private val DividerColor = Color(0xFF2A2A3A)

@Composable
fun PostScreen(viewModel: PostViewModel) {

    val creditCards by viewModel.creditCards.observeAsState(emptyList())

    LaunchedEffect(Unit) { viewModel.fetchCreditCards() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Subtle radial glow at the top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            AccentGold.copy(alpha = 0.07f),
                            Color.Transparent
                        ),
                        radius = 600f
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize()) {

            // ── Header ────────────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Text(
                    text = "CREDIT CARDS",
                    fontSize = 11.sp,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Medium,
                    color = AccentGold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Your Portfolio",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            // ── Content ───────────────────────────────────────────────────────
            AnimatedContent(
                targetState = creditCards.isEmpty(),
                transitionSpec = { fadeIn(tween(400)) togetherWith fadeOut(tween(200)) },
                label = "content"
            ) { isEmpty ->
                if (isEmpty) {
                    LoadingState()
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 20.dp,
                            vertical = 8.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(creditCards) { index, card ->
                            AnimatedCardItem(
                                index   = index,
                                title   = card.title,
                                body    = card.body
                            )
                        }
                        item { Spacer(Modifier.height(24.dp)) }
                    }
                }
            }
        }
    }
}

// ── Animated Card ─────────────────────────────────────────────────────────────
@Composable
private fun AnimatedCardItem(index: Int, title: String, body: String) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(index * 80L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter   = fadeIn(tween(400)) + slideInVertically(
            initialOffsetY = { it / 3 },
            animationSpec  = tween(400, easing = EaseOutCubic)
        )
    ) {
        CreditCardItem(title = title, body = body)
    }
}

// ── Card Item ─────────────────────────────────────────────────────────────────
@Composable
private fun CreditCardItem(title: String, body: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation   = 8.dp,
                shape       = RoundedCornerShape(16.dp),
                ambientColor= AccentGold.copy(alpha = 0.05f),
                spotColor   = AccentGold.copy(alpha = 0.05f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
    ) {
        // Left accent stripe
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(48.dp)
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(AccentGold, AccentTeal)
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 18.dp),
        ) {
            // Card number chip placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(AccentGold.copy(0.2f), AccentTeal.copy(0.15f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text  = "★",
                    color = AccentGold,
                    fontSize = 16.sp
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = title,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = TextPrimary,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text     = body,
                    fontSize = 13.sp,
                    color    = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ── Loading State ─────────────────────────────────────────────────────────────
@Composable
private fun LoadingState() {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val alpha by infiniteTransition.animateFloat(
        initialValue   = 0.3f,
        targetValue    = 0.7f,
        animationSpec  = infiniteRepeatable(
            animation  = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer_alpha"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface.copy(alpha = alpha))
            )
        }
    }
}