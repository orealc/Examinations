<template>
  <div>
    <!-- 头部 -->
    <Header></Header>
    <!-- 主体 -->
    <div style="display: flex">
      <!-- 侧边栏 -->
      <Aside></Aside>
      <!-- 内容区域 -->
      <Mans style="flex: 1" />

    </div>
  </div>
</template>

<style>

</style>

<script>
  import Aside from "./components/Aside.vue"
  import Header from "./components/Header.vue"
  import Mans from "./view/Mans.vue"
  export default {
    name: "App",
    components: {
      Aside,
      Header,
      Mans
    }
  }
</script>
