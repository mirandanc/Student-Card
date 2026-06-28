package com.example.dne.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dne.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopbar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Logo
                Image(
                    painter = painterResource(Res.drawable.dne_logo_no_bg),
                    contentDescription = "DNE Logo",
                    modifier = Modifier.height(40.dp)
                )

                // Right Logo Group
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.une_logo),
                        contentDescription = "UNE Logo",
                        modifier = Modifier.height(30.dp)
                    )
                    Image(
                        painter = painterResource(Res.drawable.ubes_logo),
                        contentDescription = "UBES Logo",
                        modifier = Modifier.height(30.dp)
                    )
                    Image(
                        painter = painterResource(Res.drawable.ANPG),
                        contentDescription = "ANPG Logo",
                        modifier = Modifier.height(30.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = androidx.compose.ui.graphics.Color.Transparent
        )
    )
}
