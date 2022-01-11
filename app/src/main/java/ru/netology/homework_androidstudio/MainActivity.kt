package ru.netology.homework_androidstudio

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.homework_androidstudio.databinding.ActivityMainBinding
import ru.netology.homework_androidstudio.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){ post ->
            with(binding) {
                author?.text = post.author
                publisher?.text = post.published
                content?.text = post.content
                likes?.text = digitsToText(post.likes)
                reposts?.text = digitsToText(post.reposts)
                views?.text = digitsToText(post.views)

                likesIco.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
            }

        }
        binding.likesIco?.setOnClickListener {
           viewModel.like()
            }

        binding.repostsIco?.setOnClickListener {
            viewModel.repostAdd()
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