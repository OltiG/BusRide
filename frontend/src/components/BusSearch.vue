<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <h3 class="card-title mb-4">Search Buses</h3>
            <form @submit.prevent="searchSchedules">
              <div class="form-group mb-3">
                <label for="fromCity" class="form-label">From</label>
                <select id="fromCity" class="form-select" v-model="fromCity" required>
                  <option value="">Select city</option>
                  <option v-for="city in cities" :key="city" :value="city">
                    {{ city }}
                  </option>
                </select>
              </div>
              <div class="form-group mb-3">
                <label for="toCity" class="form-label">To</label>
                <select id="toCity" class="form-select" v-model="toCity" required>
                  <option value="">Select city</option>
                  <option v-for="city in cities" :key="city" :value="city">
                    {{ city }}
                  </option>
                </select>
              </div>
              <div class="form-group mb-3">
                <label for="date" class="form-label">Date</label>
                <input
                  type="date"
                  id="date"
                  class="form-control"
                  v-model="date"
                  required
                >
              </div>
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" :disabled="loading">
                  {{ loading ? 'Searching...' : 'Search' }}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-8">
        <div v-if="loading" class="text-center">
          <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <div v-else-if="schedules.length === 0" class="alert alert-info">
          No schedules found. Please try different search criteria.
        </div>
        <div v-else>
          <div v-for="schedule in schedules" :key="schedule.id" class="card mb-3">
            <div class="card-body">
              <div class="row">
                <div class="col-md-8">
                  <h5 class="card-title">{{ schedule.route.startLocation }} → {{ schedule.route.endLocation }}</h5>
                  <p class="card-text">
                    <strong>Departure:</strong> {{ formatTime(schedule.departureTime) }}<br>
                    <strong>Arrival:</strong> {{ formatTime(schedule.arrivalTime) }}<br>
                    <strong>Price:</strong> ${{ schedule.currentPrice }}<br>
                    <strong>Available Seats:</strong> {{ schedule.availableSeats }}
                  </p>
                </div>
                <div class="col-md-4 text-end">
                  <button 
                    class="btn btn-success"
                    @click="bookTicket(schedule)"
                    :disabled="schedule.availableSeats === 0"
                  >
                    Book Now
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useAuthStore } from '../stores/auth'

const authStore = useAuthStore()
const fromCity = ref('')
const toCity = ref('')
const date = ref('')
const schedules = ref([])
const loading = ref(false)
const cities = ref(['Prishtina', 'Prizren', 'Peja', 'Gjakova', 'Gjilan', 'Ferizaj', 'Mitrovica'])

const searchSchedules = async () => {
  try {
    loading.value = true
    const response = await axios.get('/api/schedules/search', {
      params: {
        fromCity: fromCity.value,
        toCity: toCity.value,
        date: date.value
      },
      headers: {
        Authorization: `Bearer ${authStore.token}`
      }
    })
    schedules.value = response.data
  } catch (error) {
    console.error('Error searching schedules:', error)
    alert('Failed to search schedules. Please try again.')
  } finally {
    loading.value = false
  }
}

const formatTime = (time) => {
  return new Date(time).toLocaleTimeString()
}

const bookTicket = (schedule) => {
  // Implement booking logic
  alert('Booking functionality to be implemented')
}

onMounted(() => {
  // Set default date to today
  const today = new Date()
  date.value = today.toISOString().split('T')[0]
})
</script> 