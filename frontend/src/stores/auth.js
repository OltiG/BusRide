import { defineStore } from 'pinia'
import axios from 'axios'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: JSON.parse(localStorage.getItem('user')) || null,
    token: localStorage.getItem('token') || null
  }),

  actions: {
    async login(email, password) {
      try {
        const response = await axios.post('/api/auth/login', { email, password })
        this.user = {
          email: response.data.email,
          role: response.data.role
        }
        this.token = response.data.token
        localStorage.setItem('user', JSON.stringify(this.user))
        localStorage.setItem('token', this.token)
        return true
      } catch (error) {
        console.error('Login failed:', error)
        throw error
      }
    },

    async register(userData) {
      try {
        const response = await axios.post('/api/auth/register', userData)
        return response.data
      } catch (error) {
        console.error('Registration failed:', error)
        throw error
      }
    },

    logout() {
      this.user = null
      this.token = null
      localStorage.removeItem('user')
      localStorage.removeItem('token')
    }
  },

  getters: {
    isAuthenticated: (state) => !!state.token,
    userRole: (state) => state.user?.role
  }
}) 