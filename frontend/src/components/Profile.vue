<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card mt-5">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">Profile</h2>
            <div v-if="user" class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label class="form-label">First Name</label>
                  <p class="form-control-static">{{ user.firstName }}</p>
                </div>
                <div class="mb-3">
                  <label class="form-label">Last Name</label>
                  <p class="form-control-static">{{ user.lastName }}</p>
                </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                  <label class="form-label">Email</label>
                  <p class="form-control-static">{{ user.email }}</p>
                </div>
                <div class="mb-3">
                  <label class="form-label">Phone Number</label>
                  <p class="form-control-static">{{ user.phoneNumber }}</p>
                </div>
              </div>
            </div>
            <div class="text-center mt-4">
              <button class="btn btn-danger" @click="handleLogout">Logout</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const user = ref(null)

const fetchUserProfile = async () => {
  try {
    const response = await axios.get('/api/users/profile', {
      headers: {
        Authorization: `Bearer ${authStore.token}`
      }
    })
    user.value = response.data
  } catch (error) {
    console.error('Error fetching profile:', error)
    router.push('/login')
  }
}

const handleLogout = () => {
  authStore.logout()
  router.push('/login')
}

onMounted(fetchUserProfile)
</script> 