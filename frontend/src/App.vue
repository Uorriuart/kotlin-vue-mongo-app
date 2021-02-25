<template>
  <div id="app">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
      <div class="navbar-header">
        <router-link to="/" class="navbar-brand">Blog</router-link>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <li>
            <router-link to="/posts" class="nav-link">Posts</router-link>
          </li>
          <li>
            <router-link to="/add" class="nav-link">Add</router-link>
          </li>
        </ul>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
