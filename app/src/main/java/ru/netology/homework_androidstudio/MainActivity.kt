package ru.netology.homework_androidstudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.homework_androidstudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likes = 190,
            reposts = 567_188,
            views = 18_486_245,
            likedByMe = true
        )

        with(binding) {
            author?.text = post.author
            publisher?.text = post.published
            content?.text = post.content
            likes?.text = digitsToText(post.likes)
            reposts?.text = digitsToText(post.reposts)
            views?.text = digitsToText(post.views)

            val likedIconResID =
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            likes_ico?.setImageResource(likedIconResID)

            likes_ico?.setOnClickListener {
                post.likedByMe = !post.likedByMe

                when(post.likedByMe){
                    true -> post.likes = post.likes + 1
                    false -> post.likes = post.likes - 1
                }

                likes?.text = digitsToText(post.likes)
                likes_ico.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
            }
            reposts_ico?.setOnClickListener {
                post.reposts = post.reposts + 1
                reposts?.text = digitsToText(post.reposts)
            }
        }
    }

    private fun digitsToText(digitsToString: Long): String {
        when (digitsToString) {
            in 0..999 -> return digitsToString.toString().take(3)
            in 1000..999999 -> {
                var thousands = Math.round((digitsToString / 1000).toDouble())
                var thousandsSecond = Math.round((digitsToString % 1000).toDouble())
                if (thousands.equals(0)) {
                    thousands.toString().take(3) + "K"
                } else return return thousands.toString() + "." + thousandsSecond.toString()
                    .take(1) + "K"
            }
            else -> {
                var millions = Math.round((digitsToString / 1_000_000).toDouble())
                var millionsSecond = Math.round((digitsToString % 1_000_000).toDouble())
                if (millions.equals(0)) {
                    millions.toString().take(3) + "M"
                } else return return millions.toString() + "." + millionsSecond.toString()
                    .take(1) + "M"
            }
        }
        return digitsToString.toString().take(3)
    }
}