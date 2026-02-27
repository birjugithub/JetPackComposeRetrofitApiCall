📱 PostScreen – Credit Card UI (Jetpack Compose)
A beautifully designed credit card listing screen built with Jetpack Compose and MVVM architecture.
✨ Features

Staggered card entry animations with fade + slide-in effects
Shimmer loading skeleton while data is being fetched
Dark luxury UI with gold & teal accent system
Smooth animated transition between loading and data states
Clean card layout with gradient accent stripe and icon chip
Fully reactive using LiveData + observeAsState

🛠 Tech Stack

Jetpack Compose
ViewModel + LiveData
Kotlin Coroutines (LaunchedEffect)
Material 3
MVVM Pattern

🚀 How It Works
PostScreen observes a LiveData list of credit cards from PostViewModel. On launch, it triggers fetchCreditCards() which loads data from the repository. While loading, a shimmer skeleton is displayed. Once data arrives, cards animate in sequentially with a staggered delay for a polished user experience.<img width="405" height="879" alt="Screenshot 2026-02-27 at 11 34 53 PM" src="https://github.com/user-attachments/assets/56ffedf2-296c-4c01-94b3-2379acaffccb" />
