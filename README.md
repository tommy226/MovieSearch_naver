# MovieSearch_naver

재미로 만든 영화 검색 (네이버 기반)

![movie_naver_resize](https://user-images.githubusercontent.com/86557597/131806163-ed7766ee-fe30-4467-b549-d9e85bd9d248.gif)

#### Libarary
* retrofit2 : server api
* glide : server image
* gson : json converter

##### MVVM 패턴
View <-> View Model <-> Model

__View(Activity)가 최대한 로직에 관여하지 않아야 한다.__



```kotlin
MainActivity.kt

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val TAG = MainActivity::class.java.simpleName

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.toast.observe(this, EventObserver { toast ->
            showToast(toast)
        })

        viewModel.liveUri.observe(this, Observer { uri ->
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
    }
}
```

### 사용법

https://developers.naver.com/docs/search/movie/

API key 취득 후

CLIENT_ID , CLIENT_SECRET 값 입력

```kotlin
object NaverImpl {
    private const val BASE_URL = "https://openapi.naver.com/"
    private const val CLIENT_ID = ""
    private const val CLIENT_SECRET = ""
 ...
 }
```
