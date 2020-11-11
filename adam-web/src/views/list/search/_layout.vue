<template>
  <my-wrapper title="搜索列表"
              :links="links"
              :default-active-link="defaultActiveLink"
              @link-click="handleLinkClick">
    <template v-slot:extra>
      <div class="search-box" @keyup.enter="handleSearch">
        <el-input v-model.trim="keyword" placeholder="请输入" clearable>
          <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
        </el-input>
      </div>
    </template>
    <router-view></router-view>
  </my-wrapper>
</template>

<script>
  export default {
    data() {
      return {
        keyword: decodeURIComponent(this.$route.query.keyword || ''),
        defaultActiveLink: this.$route.path,
        links: [
          {
            label: '文章', value: '/list/search/article'
          },
          {
            label: '项目', value: '/list/search/project'
          },
          {
            label: '应用', value: '/list/search/application'
          }
        ]
      }
    },
    methods: {
      handleLinkClick(link) {
        this.$router.push({
          path: link.value,
          query: {
            keyword: encodeURIComponent(this.keyword)
          }
        })
      },
      handleSearch() {
        this.$router.push({
          query: {
            keyword: encodeURIComponent(this.keyword)
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .search-box {
    max-width: 500px;
    margin: auto;
  }
</style>
