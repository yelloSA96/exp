import { AbsoluteFill, interpolate, spring, useCurrentFrame, useVideoConfig } from "remotion";

export const OutroCard: React.FC = () => {
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
      }}
    >
      <div
        style={{
          transform: `scale(${scale})`,
          opacity,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 28,
          paddingLeft: 60,
          paddingRight: 60,
          textAlign: "center",
        }}
      >
        <p
          style={{
            color: "white",
            fontSize: 80,
            fontWeight: 900,
            margin: 0,
            lineHeight: 1.1,
          }}
        >
          Order Online Today
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
            color: "rgba(255,255,255,0.9)",
            fontSize: 48,
            fontWeight: 600,
            margin: 0,
          }}
        >
          DM us to order
        </p>
        <p
          style={{
            color: "rgba(255,255,255,0.8)",
            fontSize: 38,
            fontWeight: 500,
            margin: 0,
            fontStyle: "italic",
          }}
        >
          ruwisCakes.com.au
        </p>
      </div>
    </AbsoluteFill>
  );
};
