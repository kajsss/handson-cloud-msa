<template>
    <b-container class="py-5 px-5">
        <p>
            &copy; 2021 Samsung SDS
            <span class="text-muted">
                <b-link href="https://github.com/GoogleCloudPlatform/microservices-demo/">(Source Code)</b-link>
            </span>
        </p>
        <p>
            <small class="text-muted">
                This website is hosted for demo purposes only. It is not an
                actual shop. This is not an official Google project.
            </small>
        </p>
        <small class="text-muted">
            <div v-if="session_id">session-id: {{session_id}}<br/></div>
            <div v-if="request_id">request-id: {{request_id}}<br/></div>
        </small>
</b-container>
</template>

<script>
export default {
  name: 'Footer',
  props: {
    session_id: String,
    request_id: String
  }
}
</script>

<style scoped>
</style>
