package com.p1nero.tcrcore.client.gui;

import com.brass_amber.ba_bt.block.blockentity.spawner.BTAbstractSpawnerBlockEntity;
import com.mojang.blaze3d.platform.Window;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BTSpawnerBlockIndicatorRenderer implements IGuiOverlay {

    private static final Map<BlockPos, SpawnerIndicatorInfo> TARGET_SPAWNER_BLOCKS = new HashMap<>();

    public static final ResourceLocation QUEST_ICON = TCRQuests.SIDE_QUEST_1;

    private static class SpawnerIndicatorInfo {
        BlockPos pos;
        boolean isOnScreen;
        double screenX;
        double screenY;
        double lastScreenX;
        double lastScreenY;
    }

    private static class ScreenPos {
        boolean inside;
        double x;
        double y;
        double nx;
        double ny;
    }

    public static boolean hasTarget() {
        return !TARGET_SPAWNER_BLOCKS.isEmpty();
    }

    public static void addTargetSpawner(BlockPos pos) {
        if (!TARGET_SPAWNER_BLOCKS.containsKey(pos)) {
            SpawnerIndicatorInfo info = new SpawnerIndicatorInfo();
            info.pos = pos;
            TARGET_SPAWNER_BLOCKS.put(pos, info);
        }
    }

    public static void removeTargetSpawner(BlockPos pos) {
        TARGET_SPAWNER_BLOCKS.remove(pos);
    }

    public static void tick() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null) return;

        Iterator<Map.Entry<BlockPos, SpawnerIndicatorInfo>> it = TARGET_SPAWNER_BLOCKS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<BlockPos, SpawnerIndicatorInfo> entry = it.next();
            BlockPos pos = entry.getKey();
            if (!(minecraft.level.getBlockEntity(pos) instanceof BTAbstractSpawnerBlockEntity)) {
                it.remove();
                continue;
            }
            SpawnerIndicatorInfo info = entry.getValue();
            ScreenPos screenPos = calculateScreenPos(pos, minecraft.gameRenderer.getMainCamera(), minecraft.getWindow());
            if (screenPos != null) {
                info.isOnScreen = screenPos.inside;
                info.lastScreenX = info.screenX;
                info.lastScreenY = info.screenY;
                if (screenPos.inside) {
                    info.screenX = screenPos.x;
                    info.screenY = screenPos.y;
                }
            } else {
                info.isOnScreen = false;
            }
        }
    }

    private static ScreenPos calculateScreenPos(BlockPos pos, Camera camera, Window window) {
        Vec3 camPos = camera.getPosition();
        Vec3 targetPos = Vec3.atCenterOf(pos);
        Vec3 camToTarget = targetPos.subtract(camPos);

        if (camToTarget.lengthSqr() < 1.0e-4) {
            return null;
        }

        Vec3 forward = Vec3.directionFromRotation(camera.getXRot(), camera.getYRot());
        Vec3 upWorld = new Vec3(0.0, 1.0, 0.0);
        Vec3 right = forward.cross(upWorld).normalize();
        Vec3 up = right.cross(forward).normalize();

        double xCam = camToTarget.dot(right);
        double yCam = camToTarget.dot(up);
        double zCam = camToTarget.dot(forward);

        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();

        float fovDegrees = Minecraft.getInstance().options.fov().get();
        double fovRad = Math.toRadians(fovDegrees);
        double tanHalfFovY = Math.tan(fovRad / 2.0);
        double aspect = (double) screenWidth / (double) screenHeight;
        double tanHalfFovX = tanHalfFovY * aspect;

        double nx = xCam / (zCam * tanHalfFovX);
        double ny = yCam / (zCam * tanHalfFovY);

        boolean inside = zCam > 0.1 && nx >= -1.0 && nx <= 1.0 && ny >= -1.0 && ny <= 1.0;

        ScreenPos result = new ScreenPos();
        result.inside = inside;
        result.nx = nx;
        result.ny = ny;

        if (inside) {
            result.x = (nx * 0.5 + 0.5) * screenWidth;
            result.y = (-ny * 0.5 + 0.5) * screenHeight;
        }

        return result;
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if (TARGET_SPAWNER_BLOCKS.isEmpty()) return;
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();
        Camera camera = minecraft.gameRenderer.getMainCamera();
        Vec3 playerPos = camera.getPosition();

        int iconSize;

        for (SpawnerIndicatorInfo info : TARGET_SPAWNER_BLOCKS.values()) {
            boolean onScreen = info.isOnScreen;

            float drawX, drawY;

            if (onScreen) {
                ScreenPos sp = calculateScreenPos(info.pos, camera, window);
                if (sp != null && sp.inside) {
                    drawX = (float) sp.x;
                    drawY = (float) sp.y;
                } else {
                    drawX = (float) Mth.lerp(partialTick, info.lastScreenX, info.screenX);
                    drawY = (float) Mth.lerp(partialTick, info.lastScreenY, info.screenY);
                }

                Vec3 targetPos = Vec3.atCenterOf(info.pos);
                double distance = playerPos.distanceTo(targetPos);
                iconSize = (int) (16 - distance / 2.5);

                int finalIconSize = iconSize; // effective final for lambda/inner use if needed
                float finalDrawX = drawX - finalIconSize / 2.0f;
                float finalDrawY = drawY - finalIconSize / 2.0f;

                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(finalDrawX, finalDrawY, 0.0F);
                guiGraphics.blit(QUEST_ICON, 0, 0, 0, 0, finalIconSize, finalIconSize, finalIconSize, finalIconSize);
                guiGraphics.pose().popPose();

                String distanceText = (int) distance + "m";
                int textWidth = minecraft.font.width(distanceText);
                int textX = (int) (drawX - textWidth / 2);
                int textY = (int) (drawY + finalIconSize / 2 + 2);

                guiGraphics.drawString(minecraft.font, distanceText, textX, textY, 0xFFFFFFFF, true);
            }
            // "When icon BlockPos is located outside the screen then calculate interpolation based on tick"
            // The original code does not render anything when off-screen.
            // Following strictly the "calculate interpolation" instruction implies we update values in tick (done),
            // but since there's no render logic for off-screen, we just skip rendering.
        }
    }
}
