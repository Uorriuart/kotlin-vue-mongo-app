<template>
  <div class="row">
    <div class="col-md-4">
      <h4>Use search</h4>
      <div>
        <form>
          <input class="form-control mr-sm-2" type="search" placeholder="Search by title" aria-label="Search"
                 v-model="title">
          <button class="btn btn-outline-success my-2 my-sm-3" type="button"
                  @click="searchTitle()">Search
          </button>
        </form>
      </div>
    </div>

    <div class="col-md-4">
      <h4>Posts List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index === currentIndex }"
            v-for="(post, index) in posts"
            :key="index"
            @click="setActivePost(post, index)"
        >
          {{ post.title }}
        </li>
      </ul>

      <button class="btn btn-outline-danger my-2 my-sm-3" @click="removeAllPosts()">
        Remove All
      </button>
    </div>

    <div class="col-md-4">
      <div v-if="currentPost">
        <h4>Post</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentPost.title }}
        </div>
        <div>
          <label><strong>Description:</strong></label> {{ currentPost.description }}
        </div>
        <a class="btn btn-outline-primary my-2 my-sm-0" :href="`/posts/` + currentPost.id">Edit</a>
      </div>
      <div v-else>
        <p>Please click on a Post...</p>
      </div>
    </div>
  </div>
</template>

<script>
import PostDataService from "../services/PostDataService";

export default {
  name: "posts-list",
  data() {
    return {
      posts: [],
      currentPost: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrievePosts() {
      PostDataService.getAll()
          .then(response => {
            this.posts = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrievePosts();
      this.currentPost = null;
      this.currentIndex = -1;
    },

    setActivePost(post, index) {
      this.currentPost = post;
      this.currentIndex = index;
    },

    removeAllPosts() {
      PostDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    },

    searchTitle() {
      PostDataService.findByTitle(this.title)
          .then(response => {
            this.posts = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrievePosts();
  }
};
</script>
