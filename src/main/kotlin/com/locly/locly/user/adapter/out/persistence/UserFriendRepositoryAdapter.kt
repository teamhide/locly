package com.locly.locly.user.adapter.out.persistence

import com.locly.locly.user.adapter.out.persistence.jpa.UserFriendEntity
import com.locly.locly.user.adapter.out.persistence.jpa.UserFriendRepository
import com.locly.locly.user.application.port.out.GetUserFriendPersistencePort
import com.locly.locly.user.application.port.out.SaveUserFriendPersistencePort
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class UserFriendRepositoryAdapter(
    private val userFriendRepository: UserFriendRepository,
) : GetUserFriendPersistencePort, SaveUserFriendPersistencePort {
    override fun existsByUserIdAndFriendUserId(userId: Long, friendUserId: Long): Boolean {
        return userFriendRepository.existsByUserIdAndFriendUserId(userId = userId, friendUserId = friendUserId)
    }

    override fun countsByUserIdLessThan(userId: Long, count: Int): Boolean {
        val pageable = Pageable.ofSize(count + 1)
        val friends = userFriendRepository.findAllByUserId(userId = userId, pageable = pageable)
        return friends.size < count
    }

    override fun findAllFriendUserIdsByUserId(userId: Long): List<Long> {
        val friends = userFriendRepository.findAllByUserId(userId = userId)
        return friends.map { it.friendUserId }
    }

    override fun save(userId: Long, friendUserId: Long) {
        val userFriendEntity = UserFriendEntity(userId = userId, friendUserId = friendUserId)
        userFriendRepository.save(userFriendEntity)
    }
}
