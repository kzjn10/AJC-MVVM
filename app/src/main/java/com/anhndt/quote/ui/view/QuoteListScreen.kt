package com.anhndt.quote.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anhndt.quote.domain.model.Quote
import com.anhndt.quote.ui.state.QuoteListState
import com.anhndt.quote.ui.viewmodel.QuoteListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuoteListScreen(
    modifier: Modifier = Modifier,
    viewModel: QuoteListViewModel,
) {

    val lazyListState = rememberLazyListState()
    val uiState = viewModel.uiState.collectAsState()
    val isRefreshing by remember { mutableStateOf(false)}

    LaunchedEffect(Unit) {
        viewModel.getQuoteList()
    }

    QuoteLayout(modifier = modifier,
        state = uiState.value,
        lazyListState = lazyListState,
        onRefresh = {
            viewModel.getQuoteList(isForceRefresh = true)
        },
        isRefreshing = isRefreshing)
}

@Composable
fun QuoteLayout(
    modifier: Modifier = Modifier,
    state: QuoteListState,
    onRefresh: () -> Unit,
    lazyListState: LazyListState,
    isRefreshing: Boolean,
) {
    when (state) {
        QuoteListState.Loading -> {
            LoadingUI(modifier = modifier)
        }

        is QuoteListState.Success -> {
            QuoteListLayout(
                modifier = modifier,
                quotes = state.quotes ?: emptyList(),
                lazyListState = lazyListState,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            )
        }

        is QuoteListState.Error -> {
            ErrorUI(modifier = modifier)
        }
    }
}

@Composable
fun LoadingUI(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorUI(modifier: Modifier = Modifier, message: String = "Something went wrong") {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListLayout(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    lazyListState: LazyListState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    if (quotes.isEmpty()) {
        ErrorUI(modifier = modifier, message = "No quotes found")
    } else {
        val swipeToRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            modifier = modifier,
            state = swipeToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    state = swipeToRefreshState
                )
            },
        ) {
            LazyColumn(modifier = modifier, state = lazyListState) {
                items(quotes.size,
                    key = { index -> quotes[index].id }) { index ->
                    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                        Text(text = quotes[index].quote)
                        Text(text = quotes[index].author)
                    }
                }
            }
        }
    }
}