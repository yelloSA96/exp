import "./index.css";
import { Composition, CalculateMetadataFunction, staticFile } from "remotion";
import { MyComposition, MyCompositionProps, VideoClip } from "./Composition";
import { getVideoDuration } from "./lib/getVideoDuration";

const TRANSITION_FRAMES = 15;
const INTRO_FRAMES = 60;
const OUTRO_FRAMES = 90;
const FPS = 30;

const VIDEO_FILES: { file: string; message: string }[] = [
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.56.30.mp4",
    message: "Delivering Across Melbourne",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.57.05.mp4",
    message: "All Melbourne Metro Areas",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.57.26.mp4",
    message: "Fast & Reliable Delivery",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.57.31.mp4",
    message: "Fragile Cakes, Safe in Our Hands",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.57.53.mp4",
    message: "Order Online, Delivered Fresh",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.58.02.mp4",
    message: "24–48 Hour Delivery",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.58.16.mp4",
    message: "From Our Kitchen to Your Door",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.58.54.mp4",
    message: "We Come to You",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.59.08.mp4",
    message: "Covering All Melbourne Suburbs",
  },
  {
    file: "input_videos/WhatsApp Video 2026-04-12 at 10.59.31.mp4",
    message: "Book Your Delivery Today",
  },
];

const calculateMetadata: CalculateMetadataFunction<MyCompositionProps> = async ({ props }) => {
  const durations = await Promise.all(
    VIDEO_FILES.map(({ file }) => getVideoDuration(staticFile(file)))
  );

  const videos: VideoClip[] = VIDEO_FILES.map(({ file, message }, i) => ({
    src: file,
    message,
    durationInFrames: Math.ceil(durations[i] * FPS),
  }));

  // Total = intro + clips + outro - transitions overlaps
  // Transitions: 1 (intro→clip1) + 9 (between clips) + 1 (last clip→outro) = 11
  const totalClipFrames = videos.reduce((sum, v) => sum + v.durationInFrames, 0);
  const transitionCount = 11; // 1 after intro + 9 between clips + 1 before outro
  const durationInFrames =
    INTRO_FRAMES +
    totalClipFrames +
    OUTRO_FRAMES -
    transitionCount * TRANSITION_FRAMES;

  return {
    durationInFrames,
    props: {
      ...props,
      videos,
    },
  };
};

export const RemotionRoot: React.FC = () => {
  return (
    <>
      <Composition
        id="RuwiDeliveryZones"
        component={MyComposition}
        durationInFrames={300}
        fps={FPS}
        width={1080}
        height={1920}
        defaultProps={{ videos: [] }}
        calculateMetadata={calculateMetadata}
      />
    </>
  );
};
