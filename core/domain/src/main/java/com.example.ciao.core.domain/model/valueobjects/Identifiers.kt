import android.util.Patterns

@JvmInline
value class UserId(
    val value: String
)

@JvmInline
value class Password(
    val value: String
)

@JvmInline
value class Email(
    val value: String
)

fun Email.isValid(email: Email): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
}