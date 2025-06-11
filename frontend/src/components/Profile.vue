<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card mt-5">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">Profile</h2>
            <div v-if="error" class="alert alert-danger">
              {{ error }}
            </div>
            <div v-if="loading" class="text-center">
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>
            <div v-else-if="user" class="row">
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
                  <label class="form-label">Role</label>
                  <p class="form-control-static">{{ user.role }}</p>
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
const loading = ref(true)
const error = ref('')

const fetchUserProfile = async () => {
  try {
    loading.value = true
    error.value = ''
    const response = await axios.get('/api/users/profile', {
      headers: {
        Authorization: `Bearer ${authStore.token}`
      }
    })
    user.value = response.data
  } catch (error) {
    console.error('Error fetching profile:', error)
    if (error.response?.status === 401) {
      authStore.logout()
      router.push('/login')
    } else {
      error.value = error.response?.data?.message || 'Failed to fetch profile'
    }
  } finally {
    loading.value = false
  }
}

const handleLogout = () => {
  authStore.logout()
  router.push('/login')
}

onMounted(fetchUserProfile)
</script> 