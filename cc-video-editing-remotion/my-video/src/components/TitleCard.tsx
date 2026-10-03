import { AbsoluteFill, interpolate, useCurrentFrame } from "remotion";
import { fontFamily } from "../fonts";

export const TitleCard: React.FC<{
  durationInFrames: number;
  eyebrow?: string;
  title: string;
  subtitle?: string;
}> = ({ durationInFrames, eyebrow, title, subtitle }) => {
  const frame = useCurrentFrame();

  const opacity = interpolate(
    frame,
    [0, 20, durationInFrames - 20, durationInFrames],
    [0, 1, 1, 0],
    { extrapolateLeft: "clamp", extrapolateRight: "clamp" },
  );

  const lineWidth = interpolate(frame, [10, 40], [0, 120], {
    extrapolateLeft: "clamp",
    extrapolateRight: "clamp",
  });

  return (
    <AbsoluteFill
      style={{
        backgroundColor: "#0a0a0a",
        justifyContent: "center",
        alignItems: "center",
        opacity,
      }}
    >
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 22,
          fontFamily,
          color: "#f4efe6",
          textAlign: "center",
        }}
      >
        {eyebrow ? (
          <div
            style={{
              fontSize: 22,
              letterSpacing: 8,
              textTransform: "uppercase",
              opacity: 0.65,
            }}
          >
            {eyebrow}
          </div>
        ) : null}
        <div style={{ fontSize: 68, letterSpacing: 6 }}>{title}</div>
        <div
          style={{
            width: lineWidth,
            height: 1,
            background: "rgba(244,239,230,0.5)",
          }}
        />
        {subtitle ? (
          <div style={{ fontSize: 24, letterSpacing: 3, opacity: 0.75 }}>
            {subtitle}
          </div>
        ) : null}
      </div>
    </AbsoluteFill>
  );
};
