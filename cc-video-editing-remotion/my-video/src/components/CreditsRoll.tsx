import { AbsoluteFill, interpolate, useCurrentFrame } from "remotion";
import { fontFamily } from "../fonts";

const LINES: { heading?: string; text?: string; spacer?: boolean }[] = [
  { heading: "GLASS HOUSE MOUNTAINS" },
  { text: "14 September 2022" },
  { spacer: true },
  { heading: "THE ROUTE" },
  { text: "breakfast, then the long drive up" },
  { text: "Mt Ngungun trailhead" },
  { text: "moonrise through the trees" },
  { text: "the climb" },
  { text: "golden hour at the summit" },
  { text: "the walk down in the dark" },
  { spacer: true },
  { heading: "STARRING" },
  { text: "the whole crew" },
  { spacer: true },
  { heading: "WITH THANKS TO" },
  { text: "whoever packed snacks" },
  { text: "the moon, for showing up on time" },
  { spacer: true },
  { heading: "SEE YOU ON THE NEXT ONE" },
];

const LINE_HEIGHT = 54;

export const CreditsRoll: React.FC<{
  totalDurationInFrames: number;
  startDelay?: number;
}> = ({ totalDurationInFrames, startDelay = 45 }) => {
  const frame = useCurrentFrame();

  const contentHeight = LINES.length * LINE_HEIGHT;
  const travel = contentHeight + 1080;

  const y = interpolate(
    frame,
    [startDelay, totalDurationInFrames - 30],
    [1080 * 0.62, 1080 * 0.62 - travel],
    { extrapolateLeft: "clamp", extrapolateRight: "clamp" },
  );

  const edgeFade = interpolate(
    frame,
    [0, startDelay, totalDurationInFrames - 60, totalDurationInFrames - 30],
    [0, 1, 1, 0],
    { extrapolateLeft: "clamp", extrapolateRight: "clamp" },
  );

  return (
    <AbsoluteFill style={{ pointerEvents: "none" }}>
      <AbsoluteFill
        style={{
          left: "auto",
          width: "34%",
          background:
            "linear-gradient(90deg, rgba(0,0,0,0) 0%, rgba(0,0,0,0.32) 35%, rgba(0,0,0,0.32) 100%)",
          opacity: edgeFade,
        }}
      />
      <div
        style={{
          position: "absolute",
          right: "6%",
          top: y,
          width: "26%",
          display: "flex",
          flexDirection: "column",
          fontFamily,
          color: "rgba(244,239,230,0.92)",
          opacity: edgeFade,
        }}
      >
        {LINES.map((line, i) => (
          <div
            key={i}
            style={{
              height: LINE_HEIGHT,
              display: "flex",
              alignItems: "center",
              justifyContent: "flex-end",
              textAlign: "right",
              fontSize: line.heading ? 24 : 18,
              letterSpacing: line.heading ? 4 : 1,
              fontWeight: line.heading ? 700 : 400,
              opacity: line.heading ? 1 : 0.8,
              textShadow: "0 2px 10px rgba(0,0,0,0.8)",
            }}
          >
            {line.heading ?? line.text ?? ""}
          </div>
        ))}
      </div>
    </AbsoluteFill>
  );
};
