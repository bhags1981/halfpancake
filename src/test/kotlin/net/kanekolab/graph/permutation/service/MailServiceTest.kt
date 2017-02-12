package net.kanekolab.graph.permutation.service

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/01/30.
 */
class MailServiceTest {
    @Test
    fun sendMail() {
        MailService().sendMail("jungsinyu@gmail.com","jungsinyu@gmail.com","Hello","This is send mail test.")
    }

}