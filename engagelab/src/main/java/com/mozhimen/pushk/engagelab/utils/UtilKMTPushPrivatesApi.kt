package com.mozhimen.pushk.engagelab.utils

import android.content.Context
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.engagelab.privates.push.api.NotificationLayout

/**
 * @ClassName UtilKMTPushPrivatesApi
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/11
 * @Version 1.0
 */
object UtilKMTPushPrivatesApi {

    ///////////////////////////////////////////////////////////////////////////
    // state
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 启动sdk后可根据onNotificationStatus回调结果，再决定是否需要调用此借口
     *
     * 前往通知开关设置页面
     * @param context 不为空
     *
     *     // 前往通知开关设置页面，需要客户手动打开通知开关
     *     MTPushPrivatesApi.goToAppNotificationSettings(this);
     *
     *     // 继承MTCommonReceiver后，复写onNotificationStatus方法，获取通知开关状态，如果enable为true说明已经开启成功
     *     @Override
     *     public void onNotificationStatus(Context context, boolean enable) {
     *                 if(enable){
     *                         // 已设置通知开关为打开
     *                 }
     *     }
     */
    @JvmStatic
    fun goToAppNotificationSettings(context: Context) {
        MTPushPrivatesApi.goToAppNotificationSettings(context)
    }

    /**
     * 一般用于在调用turnOffPush后，需要再开启 push 推送
     * 重复调用只会生效一次，如果已经是开启状态，不会再生效
     * 如果环境没有问题，onConnectStatus会回调结果
     * 目前支持的通道：Engagelab/华为/小米/魅族/oppo/vivo
     *
     * 开启 Push 推送，并持久化存储开关状态为true，默认是true
     * @param context 不能为空
     *
     * // 开启推送，默认就是打开的
     * MTPushPrivatesApi.turnOnPush(context);
     *
     * // 继承MTCommonReceiver后，复写onConnectStatus方法，获取长连接的连接状态，如果enable为true说明已经开启成功
     * @Override
     * public void onConnectStatus(Context context, boolean enable){
     *     if(enable){
     *         // 开启 push 推送成功
     *     }
     * }
     */
    @JvmStatic
    fun turnOnPush(context: Context) {
        MTPushPrivatesApi.turnOnPush(context)
    }

    /**
     * 一般用于关闭 push 推送
     * 关闭 push 推送后，再调用init不会打开推送
     * 重复调用只会生效一次
     * 如果环境没有问题，onConnectStatus会回调结果
     * 目前支持的通道：Engagelab/华为/小米/魅族/oppo/vivo
     *
     * 关闭 push 推送，并持久化存储开关状态为false，默认是true
     * @param context 不能为空
     *
     * // 关闭推送，需要再调用turnOnPush才能开启推送
     * MTPushPrivatesApi.turnOffPush(context);
     *
     * // 继承MTCommonReceiver后，复写onConnectStatus方法，获取长连接状态，如果enable为false说明已经关闭成功
     * @Override
     * public void onConnectStatus(Context context, boolean enable){
     *     if(!enable){
     *         // 关闭 push 推送成功
     *     }
     * }
     */
    @JvmStatic
    fun turnOffPush(context: Context) {
        MTPushPrivatesApi.turnOffPush(context)
    }

    @JvmStatic
    fun turnOnGeofenceSwitch(context: Context) {
        MTPushPrivatesApi.turnOnGeofenceSwitch(context)
    }

    @JvmStatic
    fun turnOffGeofenceSwitch(context: Context) {
        MTPushPrivatesApi.turnOffGeofenceSwitch(context)
    }

    ///////////////////////////////////////////////////////////////////////////
    // tag
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 增加指定tag，累加逻辑，之前设置的标签依然存在
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param tags     标签数组，每个tag命名长度限制为40字节，最多支持设置1000个tag，且单次操作总长度不得超过5000字节
     *
     *         // 这里定义一个常量，代表增加指定tag操作
     *         private static final int SEQUENCE_TAG_ADD = 1;
     *         // 增加tag:"china","guangdong","shenzhen"
     *         MTPushPrivatesApi.addTag(context,SEQUENCE_TAG_ADD,"china","guangdong","shenzhen")
     *
     *         // 继承MTCommonReceiver后，复写onTagMessage方法，获取tag操作回调
     *         @Override
     *         public void onTagMessage(Context context, TagMessage tagMessage){
     *                 if(tagMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                         // tag操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                         return;
     *                 }
     *                 if(tagMessage.getSequence() == SEQUENCE_TAG_ADD){
     *                         // 增加指定tag成功，此时可查看操作成功的tag数组
     *                         String[] tags = tagMessage.getTags();
     *                 }
     *         }
     */
    @JvmStatic
    fun addTag(context: Context, sequence: Int, vararg tags: String) {
        MTPushPrivatesApi.addTag(context, sequence, *tags)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 删除指定tag，删除逻辑，会删除指定的标签
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param tags     标签数组，每个tag命名长度限制为40字节，最多支持设置1000个tag，且单次操作总长度不得超过5000字节
     *
     *     // 这里定义一个常量，代表删除指定tag操作
     *     private static final int SEQUENCE_TAG_DELETE = 2;
     *     // 删除tag:"china","guangdong","shenzhen"
     *     MTPushPrivatesApi.deleteTag(context,SEQUENCE_TAG_DELETE,"china","guangdong","shenzhen")
     *
     *     // 继承MTCommonReceiver后，复写onTagMessage方法，获取tag操作回调
     *     @Override
     *     public void onTagMessage(Context context, TagMessage tagMessage){
     *             if(tagMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // tag操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(tagMessage.getSequence() == SEQUENCE_TAG_DELETE){
     *                     // 删除指定tag成功，此时可查看操作成功的tag数组
     *                     String[] tags = tagMessage.getTags();
     *             }
     *     }
     */
    @JvmStatic
    fun deleteTag(context: Context, sequence: Int, vararg tags: String) {
        MTPushPrivatesApi.deleteTag(context, sequence, *tags)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 更新指定tag，覆盖逻辑，之前添加的tag会清空
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param tags     标签数组，每个tag命名长度限制为40字节，最多支持设置1000个tag，且单次操作总长度不得超过5000字节
     *
     *     // 这里定义一个常量，代表更新指定tag操作
     *     private static final int SEQUENCE_TAG_UPDATE = 3;
     *     // 更新tag:"beijing","shanghai","guangzhou","shenzhen"，之前设置的tag会失效
     *     MTPushPrivatesApi.updateTag(context,SEQUENCE_TAG_UPDATE,"beijing","shanghai","guangzhou","shenzhen")
     *
     *     // 继承MTCommonReceiver后，复写onTagMessage方法，获取tag操作回调
     *     @Override
     *     public void onTagMessage(Context context, TagMessage tagMessage){
     *             if(tagMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // tag操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(tagMessage.getSequence() == SEQUENCE_TAG_UPDATE){
     *                     // 更新指定tag成功，此时可查看操作成功的tag数组
     *                     String[] tags = tagMessage.getTags();
     *             }
     *     }
     */
    @JvmStatic
    fun updateTag(context: Context, sequence: Int, vararg tags: String) {
        MTPushPrivatesApi.updateTag(context, sequence, *tags)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 查询指定tag
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param tag      标签，每个tag命名长度限制为40字节
     */
    @JvmStatic
    fun queryTag(context: Context, sequence: Int, tag: String) {
        MTPushPrivatesApi.queryTag(context, sequence, tag)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 删除所有tag，清空逻辑，会删除所有的标签
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     *
     *     // 这里定义一个常量，代表删除所有tag操作
     *     private static final int SEQUENCE_TAG_DELETE_ALL = 5;
     *     // 删除所有tag
     *     MTPushPrivatesApi.deleteTag(context,SEQUENCE_TAG_DELETE_ALL)
     *
     *     // 继承MTCommonReceiver后，复写onTagMessage方法，获取tag操作回调
     *     @Override
     *     public void onTagMessage(Context context, TagMessage tagMessage){
     *             if(tagMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // tag操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(tagMessage.getSequence() == SEQUENCE_TAG_DELETE_ALL){
     *                     // 删除所有tag成功
     *             }
     *     }
     */
    @JvmStatic
    fun deleteAllTag(context: Context, sequence: Int) {
        MTPushPrivatesApi.deleteAllTag(context, sequence)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onTagMessage会回调结果
     *
     * 查询所有tag，获取逻辑，会获取所有标签
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     *
     *     // 这里定义一个常量，代表查询所有tag操作
     *     private static final int SEQUENCE_TAG_QUERY_ALL = 6;
     *     // 查询所有tag
     *     MTPushPrivatesApi.queryAllTag(context,SEQUENCE_TAG_QUERY_ALL)
     *
     *     // 继承MTCommonReceiver后，复写onTagMessage方法，获取tag操作回调
     *     @Override
     *     public void onTagMessage(Context context, TagMessage tagMessage){
     *             if(tagMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // tag 操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(tagMessage.getSequence() == SEQUENCE_TAG_DELETE_ALL){
     *                     // 查询所有tag成功，此时可查看操作成功的tag数组
     *                     String[] tags = tagMessage.getTags();
     *             }
     *     }
     */
    @JvmStatic
    fun queryAllTag(context: Context, sequence: Int) {
        MTPushPrivatesApi.queryAllTag(context, sequence)
    }


    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onAliasMessage会回调结果
     *
     * 设置alias
     * 同一个应用程序内，对不同的用户，建议取不同的别名。这样，尽可能根据别名来唯一确定用户
     * 不限定一个别名只能指定一个用户
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param alias    有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。限制：alias命名长度限制为 40 字节（判断长度需采用 UTF-8 编码）
     *
     *    // 这里定义一个常量，代表设置alias操作
     *     private static final int SEQUENCE_ALIAS_SET = 7;
     *     // 设置alias:"young"
     *     MTPushPrivatesApi.setAlias(context,SEQUENCE_ALIAS_SET,"young")
     *
     *     // 继承MTCommonReceiver后，复写onAliasMessage方法，获取alias操作回调
     *     @Override
     *     public void onAliasMessage(Context context, AliasMessage aliasMessage){
     *             if(aliasMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // alias操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(aliasMessage.getSequence() == SEQUENCE_ALIAS_SET){
     *                     // 设置alias成功，此时可查看操作成功的alias
     *                     String alias = 	aliasMessage.getAlias();
     *             }
     *     }
     */
    @JvmStatic
    fun setAlias(context: Context, sequence: Int, alias: String) {
        MTPushPrivatesApi.setAlias(context, sequence, alias)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onAliasMessage会回调结果
     *
     * 获取alias
     * 同一个应用程序内，对不同的用户，建议取不同的别名。这样，尽可能根据别名来唯一确定用户
     * 不限定一个别名只能指定一个用户
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     *
     *
     *     // 这里定义一个常量，代表获取alias操作
     *     private static final int SEQUENCE_ALIAS_GET = 8;
     *     // 获取alias
     *     MTPushPrivatesApi.getAlias(context,SEQUENCE_ALIAS_GET)
     *
     *     // 继承MTCommonReceiver后，复写onAliasMessage方法，获取alias操作回调
     *     @Override
     *     public void onAliasMessage(Context context, AliasMessage aliasMessage){
     *             if(aliasMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // alias 操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(aliasMessage.getSequence() == SEQUENCE_ALIAS_GET){
     *                     // 获取alias成功，此时可查看操作成功的alias
     *                     String alias = 	aliasMessage.getAlias();
     *             }
     *     }
     */
    @JvmStatic
    fun getAlias(context: Context, sequence: Int) {
        MTPushPrivatesApi.getAlias(context, sequence)
    }

    /**
     * 请先init，否则调用无效
     * 如果环境没有问题，onAliasMessage会回调结果
     *
     * 清除alias
     * 同一个应用程序内，对不同的用户，建议取不同的别名。这样，尽可能根据别名来唯一确定用户
     * 不限定一个别名只能指定一个用户
     * @param context  不能为空
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     *
     *     // 这里定义一个常量，代表清除alias操作
     *     private static final int SEQUENCE_ALIAS_CLEAR = 7;
     *     // 清除alias
     *     MTPushPrivatesApi.clearAlias(context,SEQUENCE_ALIAS_CLEAR)
     *
     *     // 继承MTCommonReceiver后，复写onAliasMessage方法，获取alias操作回调
     *     @Override
     *     public void onAliasMessage(Context context, AliasMessage aliasMessage){
     *             if(aliasMessage.getCode != MTPushPrivatesApi.Code.Success){
     *                     // alias操作失败，详细错误信息可查看MTPushPrivatesApi.Code
     *                     return;
     *             }
     *             if(aliasMessage.getSequence() == SEQUENCE_ALIAS_CLEAR){
     *                     // 清除alias成功
     *             }
     *     }
     */
    @JvmStatic
    fun clearAlias(context: Context, sequence: Int) {
        MTPushPrivatesApi.clearAlias(context, sequence)
    }

    ///////////////////////////////////////////////////////////////////////////
    // time
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道/应用在前台时的google通道（由Engagelab展示）
     *
     * 设置通知展示时间，默认任何时间都展示
     * @param context   不为空
     * @param beginHour 允许通知展示的开始时间（ 24 小时制，范围为 0 到 23 ）
     * @param endHour   允许通知展示的结束时间（ 24 小时制，范围为 0 到 23 ），beginHour不能大于等于endHour
     * @param weekDays  允许通知展示的星期数组（ 7 日制，范围为 1 到 7），空数组代表任何时候都不展示通知
     *
     *    // 设置通知展示时间为每周1/3/5/7的早上9点到晚上9点
     *     MTPushPrivatesApi.setNotificationShowTime(context, 9, 21, 1,3,5,7);
     */
    @JvmStatic
    fun setNotificationShowTime(context: Context, beginHour: Int, endHour: Int, vararg weekDays: Int) {
        MTPushPrivatesApi.setNotificationShowTime(context, beginHour, endHour, *weekDays)
    }

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道/应用在前台时的google通道（由Engagelab展示）
     *
     * 重置通知展示时间，默认任何时间都展示
     * @param context 不为空
     *
     *     // 重置通知展示时间，默认任何时间都展示
     *     MTPushPrivatesApi.resetNotificationShowTime(context);
     *
     */
    @JvmStatic
    fun resetNotificationShowTime(context: Context) {
        MTPushPrivatesApi.resetNotificationShowTime(context)
    }

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道/应用在前台时的google通道（由Engagelab展示）
     *
     * 设置通知静默时间，默认任何时间都不静默
     * @param context     不为空
     * @param beginHour   允许通知静默的开始时间，单位小时（ 24 小时制，范围为 0 到 23 ）
     * @param beginMinute 允许通知静默的开始时间，单位分钟（ 60 分钟制，范围为 0 到 59 ）
     * @param endHour     允许通知静默的结束时间，单位小时（ 24 小时制，范围为 0 到 23 ）
     * @param endMinute   允许通知静默的结束时间，单位分钟（ 60 分钟制，范围为 0 到 59 ）
     *
     *     // 设置通知静默时间为每天晚上9点半到第二天早上9点半
     *     MTPushPrivatesApi.setNotificationSilenceTime(context, 21, 30, 9, 30);
     */
    @JvmStatic
    fun setNotificationSilenceTime(context: Context, beginHour: Int, beginMinute: Int, endHour: Int, endMinute: Int) {
        MTPushPrivatesApi.setNotificationSilenceTime(context, beginHour, beginMinute, endHour, endMinute)
    }

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道/应用在前台时的google通道（由Engagelab展示）
     *
     * 重置通知静默时间，默认任何时间都不静默
     * @param context 不为空
     *
     *     // 重置通知静默时间，默认任何时间都不静默
     *     MTPushPrivatesApi.resetNotificationSilenceTime(context);
     */
    @JvmStatic
    fun resetNotificationSilenceTime(context: Context) {
        MTPushPrivatesApi.resetNotificationSilenceTime(context)
    }

    ///////////////////////////////////////////////////////////////////////////
    // notification
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道
     *
     * 设置自定义通知布局，默认使用系统通知布局
     * @param context            不为空
     * @param builderId          构建id
     * @param notificationLayout 自定义通知布局的对象，不为空
     *
     *         // 这里定义一个常量，代表构建id
     *         private static final int BUILDER_ID = 11;
     *
     *         // 构建出一个notificationLayout
     *     NotificationLayout notificationLayout = new NotificationLayout()
     *             .setLayoutId(R.layout.custom_notification_layout)        // 布局layout_id
     *             .setIconViewId(R.id.iv_notification_icon)                // 通知图标view_id
     *             .setIconResourceId(R.drawable.mtpush_notification_icon)   // 通知图标source_id
     *             .setTitleViewId(R.id.tv_notification_title)              // 通知标题view_id
     *             .setContentViewId(R.id.tv_notification_content)          // 通知内容view_id
     *             .setTimeViewId(R.id.tv_notification_time);               // 通知时间view_id
     *
     *     // 设置构建id为BUILDER_ID的自定义布局，下发通知时指定builderId为BUILDER_ID，即可改变通知布局
     *     MTPushPrivatesApi.setNotificationLayout(this.getApplicationContext(), BUILDER_ID, notificationLayout);
     */
    @JvmStatic
    fun setNotificationLayout(context: Context, builderId: Int, notificationLayout: NotificationLayout) {
        MTPushPrivatesApi.setNotificationLayout(context, builderId, notificationLayout)
    }

    /**
     * 请先init，否则调用无效
     * 目前支持的通道：Engagelab通道
     *
     * 重置自定义通知布局，默认使用系统通知布局
     * @param context   不为空
     * @param builderId 自定义通知布局的id
     *
     *     // 这里定义一个常量，代表构建id
     *     private static final int BUILDER_ID = 11;
     *
     *     // 重置构建id为BUILDER_ID的自定义布局，下发通知时指定builderId为BUILDER_ID，将使用系统默认布局
     *     MTPushPrivatesApi.resetNotificationLayout(context,BUILDER_ID);
     */
    @JvmStatic
    fun resetNotificationLayout(context: Context, builderId: Int) {
        MTPushPrivatesApi.resetNotificationLayout(context, builderId)
    }
}