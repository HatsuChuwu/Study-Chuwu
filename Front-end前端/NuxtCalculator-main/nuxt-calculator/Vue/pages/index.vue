<template>
  <div class="container">
    <Calculator />
  </div>
</template>

<script setup>
definePageMeta({
  layout: false
})
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 1rem;
}
</style>