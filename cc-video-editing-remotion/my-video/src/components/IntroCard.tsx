import { AbsoluteFill, interpolate, spring, useCurrentFrame, useVideoConfig } from "remotion";

export const IntroCard: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const scale = spring({
    frame,
    fps,
    config: { damping: 14, stiffness: 120 },
    from: 0.85,
    to: 1,
  });

  const opacity = interpolate(frame, [0, 12], [0, 1], {
    extrapolateRight: "clamp",
  });

  return (
    <AbsoluteFill
      style={{
        backgroundColor: "#f43f5e",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        gap: 24,
      }}
    >
      <div
        style={{
          transform: `scale(${scale})`,
          opacity,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 20,
          paddingLeft: 60,
          paddingRight: 60,
          textAlign: "center",
        }}
      >
        <p
          style={{
            color: "white",
            fontSize: 96,
            fontWeight: 900,
            fontStyle: "italic",
            margin: 0,
            lineHeight: 1,
            letterSpacing: "-2px",
          }}
        >
          Ruwi's Cakes
        </p>
        <div
          style={{
            width: 100,
            height: 6,
            backgroundColor: "rgba(255,255,255,0.6)",
            borderRadius: 3,
          }}
        />
        <p
          style={{
            color: "rgba(255,255,255,0.92)",
            fontSize: 52,
            fontWeight: 600,
            margin: 0,
            lineHeight: 1.25,
          }}
        >
          We Deliver Across{"\n"}All Melbourne
        </p>
      </div>
    </AbsoluteFill>
  );
};
