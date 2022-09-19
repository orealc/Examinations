<template>
    <div style="width: 100%;height: 700px;border: 1px solid red;">
        <div style="border: 1px solid blue;width: 15%;height: 680px;margin: 10px;">
            <div style="border: 1px solid;width: 100%;height: 30px;margin-top: 10px;" >
                <span style="font-size: 20px;">单选题</span>
                <span style="font-size: 15px;padding-left: 45px;">总10题/共100分</span>
                <div style="border: 1px solid red;width: 120px;height: 600px;margin-top: 30px;">
                    <template v-for="(item,index) in 10">
                        <div style="border: 1px solid red;width: 30px;height: 20px;margin: 10px">
                            {{item}}
                        </div>
                    </template>
                </div>
            </div>
        </div>
        <div style="border: 1px solid blue;width: 70%;height: 680px;margin: 10px;">
            <div style="border: 1px solid;width: 96%;height: 40px;margin: 20px;background-color: #ECF2F8 ">
                <span style="line-height: 40px;">单选题(本题分值: 10分)</span>
            </div>
        </div>
        <div style="border: 1px solid gold;width: 10%;height: 680px;margin: 10px;">

        </div>
    </div>
</template>

<script>
    export default {
        name: "DaTi"
    }
</script>

<style scoped>
div{
    float: left;
}
</style>