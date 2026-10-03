import {
  AbsoluteFill,
  Img,
  OffthreadVideo,
  interpolate,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import type { Shot } from "../data/shots";
import { fontFamily } from "../fonts";

const KEN_BURNS_START_SCALE = 1;
const KEN_BURNS_END_SCALE = 1.14;
const PAN_DISTANCE = 6; // percent of frame width/height

const getTransform = (
  frame: number,
  durationInFrames: number,
  pan: Shot["pan"],
) => {
  const progress = interpolate(frame, [0, durationInFrames], [0, 1], {
    extrapolateLeft: "clamp",
    extrapolateRight: "clamp",
  });
  const scale = interpolate(
    progress,
    [0, 1],
    pan === "out"
      ? [KEN_BURNS_END_SCALE, KEN_BURNS_START_SCALE]
      : [KEN_BURNS_START_SCALE, KEN_BURNS_END_SCALE],
  );

  let x = 0;
  let y = 0;
  if (pan === "left") {
    x = interpolate(progress, [0, 1], [PAN_DISTANCE / 2, -PAN_DISTANCE / 2]);
  } else if (pan === "right") {
    x = interpolate(progress, [0, 1], [-PAN_DISTANCE / 2, PAN_DISTANCE / 2]);
  } else {
    y = interpolate(progress, [0, 1], [PAN_DISTANCE / 4, -PAN_DISTANCE / 4]);
  }

  return `scale(${scale}) translate(${x}%, ${y}%)`;
};

const LETTERBOX_HEIGHT = "5.2%";

export const MediaShot: React.FC<{ shot: Shot }> = ({ shot }) => {
  const frame = useCurrentFrame();
  const { width: compW, height: compH, fps } = useVideoConfig();
  const compAspect = compW / compH;
  const mediaAspect = shot.width / shot.height;
  const isCloseToFrame = Math.abs(mediaAspect - compAspect) < 0.08;

  const transform = getTransform(frame, shot.durationInFrames, shot.pan);

  const Media =
    shot.type === "image" ? (
      <Img
        src={shot.src}
        style={{
          width: "100%",
          height: "100%",
          objectFit: isCloseToFrame ? "cover" : "contain",
          transform,
        }}
      />
    ) : (
      <OffthreadVideo
        src={shot.src}
        muted
        startFrom={Math.round((shot.startFromSeconds ?? 0) * fps)}
        style={{
          width: "100%",
          height: "100%",
          objectFit: isCloseToFrame ? "cover" : "contain",
          transform,
        }}
      />
    );

  const Background = !isCloseToFrame ? (
    <AbsoluteFill>
      {shot.type === "image" ? (
        <Img
          src={shot.src}
          style={{
            width: "100%",
            height: "100%",
            objectFit: "cover",
            filter: "blur(40px) brightness(0.45) saturate(1.1)",
            transform: "scale(1.2)",
          }}
        />
      ) : (
        <OffthreadVideo
          src={shot.src}
          muted
          startFrom={Math.round((shot.startFromSeconds ?? 0) * fps)}
          style={{
            width: "100%",
            height: "100%",
            objectFit: "cover",
            filter: "blur(40px) brightness(0.45) saturate(1.1)",
            transform: "scale(1.2)",
          }}
        />
      )}
    </AbsoluteFill>
  ) : null;

  return (
    <AbsoluteFill style={{ backgroundColor: "#000" }}>
      {Background}
      <AbsoluteFill
        style={{
          filter: "saturate(1.08) contrast(1.05)",
        }}
      >
        {Media}
      </AbsoluteFill>

      {/* cinematic letterbox bars */}
      <div
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          right: 0,
          height: LETTERBOX_HEIGHT,
          background: "#000",
        }}
      />
      <div
        style={{
          position: "absolute",
          bottom: 0,
          left: 0,
          right: 0,
          height: LETTERBOX_HEIGHT,
          background: "#000",
        }}
      />

      {shot.caption ? (
        <AbsoluteFill
          style={{
            justifyContent: "flex-end",
            alignItems: "flex-start",
            padding: "0 0 9% 6%",
          }}
        >
          <div
            style={{
              fontFamily,
              color: "rgba(255,255,255,0.92)",
              fontSize: 34,
              letterSpacing: 4,
              textShadow: "0 2px 12px rgba(0,0,0,0.8)",
              opacity: interpolate(
                frame,
                [8, 28, shot.durationInFrames - 14, shot.durationInFrames],
                [0, 1, 1, 0],
                { extrapolateLeft: "clamp", extrapolateRight: "clamp" },
              ),
            }}
          >
            {shot.caption}
          </div>
        </AbsoluteFill>
      ) : null}
    </AbsoluteFill>
  );
};
