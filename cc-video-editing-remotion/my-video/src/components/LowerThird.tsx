import { interpolate, useCurrentFrame } from "remotion";

interface LowerThirdProps {
  message: string;
}

export const LowerThird: React.FC<LowerThirdProps> = ({ message }) => {
  const frame = useCurrentFrame();

  const slideY = interpolate(frame, [0, 15], [60, 0], {
    extrapolateRight: "clamp",
  });
  const opacity = interpolate(frame, [0, 12], [0, 1], {
    extrapolateRight: "clamp",
  });

  return (
    <div
      style={{
        position: "absolute",
        bottom: 80,
        left: 0,
        right: 0,
        transform: `translateY(${slideY}px)`,
        opacity,
      }}
    >
      <div
        style={{
          backgroundColor: "#f43f5e", // rose-500
          paddingLeft: 48,
          paddingRight: 48,
          paddingTop: 20,
          paddingBottom: 20,
        }}
      >
        <p
          style={{
            color: "white",
            fontSize: 44,
            fontWeight: 800,
            margin: 0,
            letterSpacing: "-0.5px",
            lineHeight: 1.2,
          }}
        >
          {message}
        </p>
        <p
          style={{
            color: "rgba(255,255,255,0.85)",
            fontSize: 28,
            fontWeight: 500,
            margin: 0,
            marginTop: 4,
          }}
        >
          ruwisCakes.com.au
        </p>
      </div>
    </div>
  );
};
